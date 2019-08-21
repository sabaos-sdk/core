# SabaOS SDK: core libraries

[![](https://jitpack.io/v/sabaos-sdk/core.svg)](https://jitpack.io/#sabaos-sdk/core)

SabaOS is a fork of Android operating system focusing on security and usability.
SabaOS SDK enables application developers to integrate their apps with SabaOS and
also use special features available in Saba phones.

Core libraries is the most common part of SabaOS SDK that provides basic functionality
like detecting SabaOS specific properties. This library is a prerequisite for most of
other libraries in SabaOS SDK.


## Provided Functionality

### SabaOS Detection
To check if the OS is SabaOS, `SabaUtils.isSabaBuild` method can be used. This is useful
when using OS-specific features to provide fallbacks for users of all OS types. For example:

```
if (SabaUtils.isSabaBuild()) {
    // Use SabaOS specific features...
} else {
    // Fallback code or warning message...
}
```

### Security Level Detection
"Security Level" is an important concept in SabaOS that determines accessible features
and security policies for the OS. Based on security level, SabaOS provides different
user experience for different types of users. To inspect the security level, the
`SabaUtils.getSecurityLevel` method can be used. For example:

```
int securityLevel = SabaUtils.getSecurityLevel(this);
String message = "Security level: " + securityLevel.toString();
Toast.makeText(this, message, Toast.LENGTH_LONG).show();
```
		
## Usage

1. Add the JitPack repository to your root build file:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
  
2. Add the dependency:

```
	dependencies {
	        implementation 'com.github.sabaos-sdk:core:0.1.5'
	}
```