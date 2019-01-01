package com.puttysoftware.lasertank.improved.shell.loaders;

import com.puttysoftware.lasertank.improved.sound.SoundFactory;

final class SoundCacheEntry {
    // Fields
    private final SoundFactory sound;
    private final String name;

    // Constructor
    SoundCacheEntry(final SoundFactory newSound, final String newName) {
	this.sound = newSound;
	this.name = newName;
    }

    String getName() {
	return this.name;
    }

    // Methods
    SoundFactory getSound() {
	return this.sound;
    }
}