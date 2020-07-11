package com.reun.exefile.window_exe.hindiexe;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class ExecuteHindiExe {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		Thread.sleep(2000);
		// Start Winium driver
		System.out.println("Started.............");
		if (startWiniumDesktopDriver("exe-files\\Winium.Desktop.Driver.exe")) {
			DesktopOptions option = new DesktopOptions();
			System.out.println("Opening iPrep.exe...");
			option.setApplicationPath("G:\\iPrep.exe");
			WiniumDriver driver = new WiniumDriver(new URL("http://localhost:9999"), option);
			//driver.findElementById("2425558").click();
			Thread.sleep(300);
			System.out.println("click...");
			driver.findElement(By.id("2296016")).sendKeys("hello");
			Thread.sleep(1000);
			System.out.println("Text...");
			driver.findElementByClassName("TEdit").sendKeys("hello");
		}
	}

	public static boolean startWiniumDesktopDriver(String path) {
		System.out.println("============");
		boolean result = false;
		if (!path.isEmpty()) {
			Runtime runtime = Runtime.getRuntime(); // getting Runtime object
			try {
				if(available(9999)) {
					System.out.println("Going to run port 9999");
				runtime.exec(path); // opens new notepad instance
				}
				System.out.println("Port 9999 is already open...");
				result = true;
			} catch (IOException e) {
				e.printStackTrace();
				result = false;
			}

		}
		return result;
	}
	private static boolean available(int port) {
	    System.out.println("--------------Testing port " + port);
	    Socket s = null;
	    try {
	        s = new Socket("localhost", port);

	        // If the code makes it this far without an exception it means
	        // something is using the port and has responded.
	        System.out.println("--------------Port " + port + " is not available");
	        return false;
	    } catch (IOException e) {
	        System.out.println("--------------Port " + port + " is available");
	        return true;
	    } finally {
	        if( s != null){
	            try {
	                s.close();
	            } catch (IOException e) {
	                throw new RuntimeException("You should handle this error." , e);
	            }
	        }
	    }
	}
}