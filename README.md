<H1>SabaOS-SDK core</H1>

[![](https://jitpack.io/v/sabaos-sdk/core.svg)](https://jitpack.io/#sabaos-sdk/core)

This library provides a set of tools for evaluating phone's security level.
<H3>Example</H3>

        SabaUtils SecurityUtils = new SabaUtils();
        Toast.makeText(this, SecurityUtils.getSecurityLevel(this), Toast.LENGTH_LONG).show();
		
		


<br/>
<H1>Download</H1>
<H3>Step 1. Add the JitPack repository to your root build file </H3>

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
<H3>Step 2. Add the dependency</H3>

	dependencies {
	        implementation 'com.github.sabaos-sdk:core:0.1.1'
	}
