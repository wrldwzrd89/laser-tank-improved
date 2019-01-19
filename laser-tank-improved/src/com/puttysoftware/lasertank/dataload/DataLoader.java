package com.puttysoftware.lasertank.dataload;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.puttysoftware.dialogs.CommonDialogs;
import com.puttysoftware.lasertank.LaserTank;
import com.puttysoftware.lasertank.utilities.Extension;

public class DataLoader {
    private static final String LOAD_PATH = "/assets/data/";
    private static Class<?> LOAD_CLASS = DataLoader.class;
    private static ArrayList<Properties> CACHE;
    private static ArrayList<Properties> SOLID_CACHE;

    public static void initialize() {
	final int files = DataFileNames.getFileCount();
	DataLoader.CACHE = new ArrayList<>(files);
	for (int f = 0; f < files; f++) {
	    DataLoader.CACHE.add(new Properties());
	}
	DataLoader.cacheFile(DataFile.SOLID);
	DataLoader.cacheFile(DataFile.SOLID_ATTRIBUTE);
	DataLoader.cacheSolidFile(SolidDataFile.SOLID_BLUE_LASER);
	DataLoader.cacheSolidFile(SolidDataFile.SOLID_GREEN_LASER);
	DataLoader.cacheSolidFile(SolidDataFile.SOLID_HEAT_LASER);
	DataLoader.cacheSolidFile(SolidDataFile.SOLID_OTHER);
	DataLoader.cacheSolidFile(SolidDataFile.SOLID_POWER_LASER);
	DataLoader.cacheSolidFile(SolidDataFile.SOLID_RED_LASER);
	DataLoader.cacheSolidFile(SolidDataFile.SOLID_SHADOW_LASER);
    }

    private static void cacheFile(final DataFile file) {
	final int fileID = file.ordinal();
	final String filename = DataFileNames.getFileName(file);
	try (final InputStream is = DataLoader.LOAD_CLASS
		.getResourceAsStream(DataLoader.LOAD_PATH + filename + Extension.getStringsExtensionWithPeriod())) {
	    DataLoader.CACHE.get(fileID).load(is);
	} catch (final IOException ioe) {
	    CommonDialogs.showErrorDialog("Something has gone horribly wrong trying to cache object data!",
		    "FATAL ERROR");
	    LaserTank.logErrorDirectly(ioe);
	}
    }

    private static Properties getFromCache(final DataFile file) {
	final int fileID = file.ordinal();
	return DataLoader.CACHE.get(fileID);
    }

    private static void cacheSolidFile(final SolidDataFile file) {
	final int fileID = file.ordinal();
	final String filename = SolidDataFileNames.getFileName(file);
	try (final InputStream is = DataLoader.LOAD_CLASS
		.getResourceAsStream(DataLoader.LOAD_PATH + filename + Extension.getStringsExtensionWithPeriod())) {
	    DataLoader.SOLID_CACHE.get(fileID).load(is);
	} catch (final IOException ioe) {
	    CommonDialogs.showErrorDialog("Something has gone horribly wrong trying to cache object solidity data!",
		    "FATAL ERROR");
	    LaserTank.logErrorDirectly(ioe);
	}
    }

    private static Properties getFromSolidCache(final SolidDataFile file) {
	final int fileID = file.ordinal();
	return DataLoader.SOLID_CACHE.get(fileID);
    }

    public static String loadSolid(final int objID) {
	SolidDataFile sdf = SolidDataFileNames
		.getFile(DataLoader.getFromCache(DataFile.SOLID).getProperty(Integer.toString(objID)));
	return DataLoader.getFromSolidCache(sdf).getProperty(Integer.toString(objID));
    }

    public static String loadAttributeSolid(final int attrID) {
	return DataLoader.getFromCache(DataFile.SOLID_ATTRIBUTE).getProperty(Integer.toString(attrID));
    }
}
