package com.voice.cognitive;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.microsoft.cognitiveservices.speech.CancellationDetails;
import com.microsoft.cognitiveservices.speech.CancellationReason;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognitionResult;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;

public class Program {

	public static void main(String[] args) {
		SpeechConfig speechConfig = SpeechConfig.fromSubscription("6801cbcf30834a368c0295c180987513", "eastus");
		try {
			fromMic(speechConfig);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// fromFile(speechConfig);
	}

	public static void fromMic(SpeechConfig speechConfig) throws InterruptedException, ExecutionException {
		AudioConfig audioConfig = AudioConfig.fromDefaultMicrophoneInput();
		@SuppressWarnings("resource")
		SpeechRecognizer recognizer = new SpeechRecognizer(speechConfig, audioConfig);

//context free and context specific grammer.?? xml is sensitive grammer.
		System.out.println("Speak into your microphone.");
		Future<SpeechRecognitionResult> task = recognizer.recognizeOnceAsync();

		SpeechRecognitionResult result;
		audioConfig.close();
		result = task.get();
		System.out.println("RECOGNIZED: Text=" + result.getText());

		switch (result.getReason()) {
		case RecognizedSpeech:
			System.out.println("We recognized: " + result.getText());
			int exitCode = 0;
			break;
		case NoMatch:
			System.out.println("NOMATCH: Speech could not be recognized.");
			break;
		case Canceled: {
			CancellationDetails cancellation = CancellationDetails.fromResult(result);
			System.out.println("CANCELED: Reason=" + cancellation.getReason());

			if (cancellation.getReason() == CancellationReason.Error) {
				System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
				System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
				System.out.println("CANCELED: Did you update the subscription info?");
			}
		}
			break;
		}

	}

	public static void fromFile(SpeechConfig speechConfig) {

		AudioConfig audioConfig = AudioConfig.fromWavFileInput("/Users/priyal/Downloads/male.wav");
		SpeechRecognizer recognizer = new SpeechRecognizer(speechConfig, audioConfig);

		Future<SpeechRecognitionResult> task = recognizer.recognizeOnceAsync();
		SpeechRecognitionResult result;
		try {
			result = task.get();
			System.out.println("RECOGNIZED: Text=" + result.getText());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}