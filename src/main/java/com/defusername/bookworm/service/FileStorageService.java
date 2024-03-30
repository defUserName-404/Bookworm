package com.defusername.bookworm.service;

import com.defusername.bookworm.util.logging.LoggerManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class FileStorageService {

	private static FileStorageService INSTANCE;

	private FileStorageService() {

	}

	public static FileStorageService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FileStorageService();
		}
		return INSTANCE;
	}

	public void save(MultipartFile file, Path filePath) {
		try {
			Files.write(filePath, file.getBytes());
		} catch (IOException e) {
			LoggerManager.getInstance()
						 .getLogger(FileStorageService.class)
						 .error(Arrays.toString(e.getStackTrace()));
		}
	}

	public void download(Path sourceFilePath, String downloadLocation, String fileName) {
		final Path downloadPath = Paths.get(downloadLocation, fileName);
		try {
			Files.copy(sourceFilePath, downloadPath);
		} catch (IOException e) {
			LoggerManager.getInstance()
						 .getLogger(FileStorageService.class)
						 .error("File download failed: \n{}", Arrays.toString(e.getStackTrace()));
		}
	}

}
