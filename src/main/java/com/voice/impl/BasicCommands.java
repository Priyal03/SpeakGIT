package com.voice.impl;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Ref;

public class BasicCommands {

	public static void main(String[] args) {

		/*
		 * git clone command
		 * 
		 * inputs:repo-URL
		 * 
		 */

		try {

			Git git = Git.cloneRepository().setURI("https://github.com/eclipse/jgit.git")
					.setDirectory(new File("/Users/priyal/eclipse-workspace/JGIT")).call();

			System.out.println("Git Clone Executed Successfully !!!");

		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			//System.out.println("Exception at " + e.getMessage());
			e.printStackTrace();
		}
	}
}
