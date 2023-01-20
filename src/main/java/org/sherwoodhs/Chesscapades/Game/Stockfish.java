package org.sherwoodhs.Chesscapades.Game;

import java.io.*;
import java.util.stream.Stream;

/**
 * A simple and efficient client to run Stockfish from Java
 * 
 * @author Rahul A R
 * 
 */
public class Stockfish {

	private Process engineProcess;
	private BufferedReader processReader;
	private OutputStreamWriter processWriter;

	private static final String PATH = "src/main/java/org/sherwoodhs/Chesscapades/stockfish/stockfish-windows-2022-x86-64-avx2.exe";

	/**
	 * Starts Stockfish engine as a process and initializes it
	 *
	 */
	public void startEngine() {
		try
		{
			String[] commands = {PATH};
			engineProcess = Runtime.getRuntime().exec(commands);
			processReader = new BufferedReader(new InputStreamReader(
					engineProcess.getInputStream()));
			processWriter = new OutputStreamWriter(
					engineProcess.getOutputStream());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Takes in any valid UCI command and executes it
	 * 
	 * @param command
	 */
	public void sendCommand(String command) {
		try {
			processWriter.write(command + "\n");
			processWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is generally called right after 'sendCommand' for getting the raw
	 * output from Stockfish
	 * 
	 * @param waitTime
	 *            Time in milliseconds for which the function waits before
	 *            reading the output. Useful when a long running command is
	 *            executed
	 * @return Raw output from Stockfish
	 */
	public String getBestMoveOutput(int waitTime) {
		try {
			Thread.sleep(waitTime);
			sendCommand("isready");
			int i = 0;
			return processReader.lines().filter(text -> text.startsWith("bestmove")).findFirst().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * This function returns the best move for a given position after
	 * calculating for 'waitTime' ms
	 * 
	 * @param fen
	 *            Position string
	 * @param waitTime
	 *            in milliseconds
	 * @return Best Move in PGN format
	 */
	public String getBestMove(String fen, int waitTime) {
		sendCommand("position fen " + fen);
		try{
			Thread.sleep(10);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		sendCommand("go movetime " + waitTime);
		try {
			Thread.sleep(waitTime + 20);
			sendCommand("isready");
			int i = 0;
			return processReader.lines().filter(text -> text.startsWith("bestmove")).findFirst().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getBestMoveCode(String fen, int waitTime) {
		String toParse = getBestMove(fen, waitTime);
		String result;
		if (toParse.length() > 13) {
			result = new String(new char[]{toParse.charAt(9), toParse.charAt(10), toParse.charAt(11), toParse.charAt(12), toParse.charAt(13)});
		}
		else
		{
			result = new String(new char[]{toParse.charAt(9), toParse.charAt(10), toParse.charAt(11), toParse.charAt(12), ' '});
		}
		return result;
	}

	/**
	 * Stops Stockfish and cleans up before closing it
	 */
	public void stopEngine() {
		try {
			sendCommand("quit");
			processReader.close();
			processWriter.close();
		} catch (IOException e) {
		}
	}
}
