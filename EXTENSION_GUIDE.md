# 📱 SRC Kodular Extension — Complete Build Guide
## Shree Ram Computers (SRC), Daurala, Meerut
### GitHub Repository: https://github.com/shreeramcomputers/src-extensions

---

> **GOLDEN RULE:** Koi bhi software install nahi karna hai.
> Sab kuch **100% online** hoga — sirf browser chahiye.
> AI (Claude/ChatGPT) se code likhwao, GitHub par paste karo, done!

---

## 📌 SECTION 1 — Basic Concepts (Ek Baar Padho, Hamesha Yaad Rakho)

### Extension kya hoti hai?
- Kodular mein jo default blocks nahi hote, unhe **Extension (.aix file)** ke zariye add karte hain
- Extension ek **Java code ka package** hota hai jise `.aix` format mein compile kiya jata hai
- `.aix` file Kodular mein import karke use karte hain

### Hum kya use karte hain?
| Tool | Kaam | Website |
|---|---|---|
| **GitHub** | Code store + Build server | github.com |
| **GitHub Actions** | Automatic build runner | (GitHub ke andar) |
| **Rush CLI** | `.aix` file banata hai | Auto-install hota hai |
| **Java 11** | Compiler | Auto-install hota hai |
| **AI (Claude)** | Java code likhta hai | claude.ai |

### Rush CLI kya hai?
- Rush ek **build tool** hai jo Java code ko `.aix` extension mein convert karta hai
- GitHub Actions par **automatic install** ho jata hai — apne computer par kuch nahi karna
- Official source: https://github.com/shreyashsaitwal/rush-cli

---

## 📌 SECTION 2 — Rush Extension: Poora Technical Reference

### 2.1 — Language & Version

```
Language    : Java (version 8 compatible code likhna — Java 8 features use karo)
JDK Version : 11 (GitHub Actions par automatic setup hota hai)
Rush Branch : main (stable)
Min Android : SDK 21 (Android 5.0 Lollipop aur upar)
```

### 2.2 — Annotations Rules (BAHUT ZAROORI)

Rush ne MIT App Inventor ke annotation system ko **2 parts mein tod diya:**

#### ❌ Class-Level Annotations — Ab NAHI lagate (rush.yml handle karta hai)
```java
// YEH MAT LIKHO — Rush mein galat hai:
@DesignerComponent(...)    // ❌ rush.yml mein hai
@SimpleObject(external = true)  // ❌ rush.yml mein hai
```

#### ✅ Method-Level Annotations — Abhi bhi Java mein lagate hain
```java
@SimpleFunction(description = "...")   // ✅ Block jo kuch kare
@SimpleProperty(description = "...")   // ✅ Value get/set kare
@SimpleEvent(description = "...")      // ✅ Event fire kare
```

#### ✅ Required Imports (har extension mein ye hone chahiye)
```java
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
```

### 2.3 — Java Class Template (Copy-Paste Ready)

```java
package com.src.EXTENSIONNAME;

import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

// ⚠️ Class par koi annotation NAHI lagata Rush mein
public class EXTENSIONNAME extends AndroidNonvisibleComponent {

    // Constructor — EXACTLY yahi likhna hai, kuch change mat karo
    public EXTENSIONNAME(ComponentContainer container) {
        super(container.$form());
    }

    // Function block — Kodular mein block banata hai
    @SimpleFunction(description = "Yahan function ka description likho")
    public String MyFunction(String input) {
        return input.toUpperCase();
    }

    // Property block — Get/Set value
    @SimpleProperty(description = "Yahan property ka description likho")
    public String MyProperty() {
        return "value";
    }

    // Event — Kodular mein event block banata hai
    @SimpleEvent(description = "Yahan event ka description likho")
    public void MyEvent(String data) {
        EventDispatcher.dispatchEvent(this, "MyEvent", data);
    }
}
```

### 2.4 — rush.yml Template (Complete Reference)

```yaml
# Rush Metadata File — Official Fields Reference
# Source: github.com/shreyashsaitwal/rush-cli/wiki/Metadata-File-(rush.yml)

name: ExtensionName                    # REQUIRED: Extension ka naam (spaces nahi)

description: >                         # REQUIRED: Description
  Yahan apni extension ki description
  likho. Multi-line bhi ho sakti hai.

homepage: https://shreeramcomputers.com  # Optional: Website URL

license: MIT                             # Optional: MIT / Apache-2.0 / GPL-3.0

min_sdk: 21                              # Optional: Minimum Android SDK (21 = Android 5.0)

version:
  name: "1.0.0"                         # Optional: Version name (display ke liye)
  number: 1                             # REQUIRED: Number (ya 'auto' for auto-increment)

assets:
  icon: icon.png                        # Optional: assets/ folder mein icon file

build:
  release:
    optimize: true                      # Optional: ProGuard optimization (recommended true)
  kotlin:
    enable: false                       # Optional: Kotlin support (hum Java use karte hain)
  desugar:
    enable: false                       # Optional: Java 8 lambda support
    desugar_deps: false                 # Optional: Dependencies bhi desugar karo
```

### 2.5 — AndroidManifest.xml Template

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <!--
    PERMISSION REFERENCE — Zaroorat ke hisab se uncomment karo:

    Internet access ke liye:
    <uses-permission android:name="android.permission.INTERNET" />

    Network state check ke liye:
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    Storage read ke liye:
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

    Storage write ke liye:
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

    Camera ke liye:
    <uses-permission android:name="android.permission.CAMERA" />

    Agar koi permission nahi chahiye to manifest empty chhodo (jaisa upar hai)
    -->
</manifest>
```

### 2.6 — proguard-rules.pro Template

```pro
# SRC Extension ProGuard Rules
# Ye file sirf --release build mein use hoti hai

# Apni extension ki saari public classes aur methods protect karo
-keep public class com.src.** {
    public *;
}

# MIT App Inventor runtime ko touch mat karo
-keep class com.google.appinventor.** { *; }
```

---

## 📌 SECTION 3 — Exact Folder Structure

```
src-extensions/                          ← GitHub Repository Root
│
├── .github/
│   └── workflows/
│       └── build.yml                    ← GitHub Actions (EK BAAR BANAO, BAR BAR USE KARO)
│
├── SRCValidatorUtils/                   ← Extension 1
│   ├── assets/
│   │   └── icon.png                     ← 16x16 ya 32x32 PNG icon
│   ├── deps/                            ← External JAR/AAR (khali rakhna agar nahi chahiye)
│   ├── src/
│   │   ├── com/
│   │   │   └── src/
│   │   │       └── validatorutils/
│   │   │           └── SRCValidatorUtils.java
│   │   ├── AndroidManifest.xml
│   │   └── proguard-rules.pro
│   └── rush.yml
│
├── SRCAnotherExtension/                 ← Extension 2 (future)
│   └── ... (same structure repeat)
│
├── .gitignore
└── README.md
```

### Package Naming Convention (SRC Standard)
```
Format: com.src.<extensionname>

Examples:
com.src.validatorutils     → SRCValidatorUtils ke liye
com.src.deviceinfo         → SRCDeviceInfo ke liye
com.src.pdftools           → SRCPdfTools ke liye
com.src.networkutils       → SRCNetworkUtils ke liye
```

### Java File Path Rule
```
Package name   : com.src.validatorutils
File path      : src/com/src/validatorutils/SRCValidatorUtils.java

Formula:
Package ke dots (.) ko slash (/) se replace karo
Phir className.java lagao end mein
```

---

## 📌 SECTION 4 — GitHub Actions Workflow (Complete File)

**File path:** `.github/workflows/build.yml`

```yaml
name: Build SRC Kodular Extensions

on:
  push:
    branches: [ main ]      # Jab main branch mein koi push ho
  workflow_dispatch:         # Manual run button bhi milega

jobs:
  # ═══════════════════════════════════════
  # Job 1: SRCValidatorUtils
  # ═══════════════════════════════════════
  build-validator-utils:
    name: Build SRCValidatorUtils
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Setup Java 11
        uses: actions/setup-java@v4
        with:
          java-version: '11'
          distribution: 'temurin'

      - name: Install Rush CLI
        run: |
          curl https://raw.githubusercontent.com/shreyashsaitwal/rush-cli/main/scripts/install/install.sh -fsSL | sh
          echo "$HOME/.rush/bin" >> $GITHUB_PATH

      - name: Build Extension
        run: |
          cd SRCValidatorUtils
          rush build --release

      - name: Upload .aix Artifact
        uses: actions/upload-artifact@v4
        with:
          name: SRCValidatorUtils-build${{ github.run_number }}
          path: SRCValidatorUtils/out/*.aix
          retention-days: 90

  # ═══════════════════════════════════════
  # Nayi Extension Add karne ka Tarika:
  # Upar wala job copy karo, naam aur path badlo
  # ═══════════════════════════════════════
```

---

## 📌 SECTION 5 — .gitignore File

**File path:** `.gitignore`

```gitignore
# Rush build output — Download karo GitHub Artifacts se
*/out/
*/.rush/

# OS files
.DS_Store
Thumbs.db

# IDE files
.idea/
*.iml
.vscode/
*.class
```

---

## 📌 SECTION 6 — Online Workflow: Step by Step

### Nayi Extension Banana (Online, Koi Software Nahi)

```
STEP 1: AI se Java code maango
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Claude/ChatGPT ko yeh prompt do:

"Mujhe ek Kodular Extension ka Java code chahiye:
- Extension naam: SRCMyExtension
- Package: com.src.myextension
- Rush CLI use ho raha hai (class-level annotations nahi)
- Kaam: [apna kaam describe karo]
- Required imports: SimpleFunction, SimpleProperty, SimpleEvent,
  AndroidNonvisibleComponent, ComponentContainer, EventDispatcher
- Constructor: public SRCMyExtension(ComponentContainer container)
- [Functions ki list jo chahiye]"


STEP 2: GitHub par files upload karo
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
github.com/shreeramcomputers/src-extensions par jao
"Add file" → "Create new file"
File name: SRCMyExtension/rush.yml
Content: (rush.yml template se fill karo)
"Commit changes" dabao

Yahi repeat karo:
- SRCMyExtension/src/AndroidManifest.xml
- SRCMyExtension/src/proguard-rules.pro
- SRCMyExtension/src/com/src/myextension/SRCMyExtension.java


STEP 3: GitHub Actions update karo
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
.github/workflows/build.yml file edit karo
Nayi extension ka job add karo (Section 4 ka template copy karo)


STEP 4: Build dekho
━━━━━━━━━━━━━━━━━━━
Repository → "Actions" tab → Latest run
3-5 minute wait karo
"Artifacts" section → .aix file download karo


STEP 5: Kodular mein use karo
━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Kodular.io → Designer → Extensions (left panel)
"Import Extension" → .aix file upload
Done! ✅
```

---

## 📌 SECTION 7 — AI Prompt Templates (Copy-Ready)

### Generic Extension Prompt
```
Mujhe ek Kodular Extension ka Java code chahiye jo Rush CLI ke sath kaam kare.

Details:
- Extension class naam: SRCMyExtension
- Package: com.src.myextension
- Rush use hota hai isliye class-level annotations (@DesignerComponent, @SimpleObject) NAHI likhna

Required imports (ye sab likhna ZAROORI hai):
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

Constructor (exactly yahi likhna hai):
public SRCMyExtension(ComponentContainer container) {
    super(container.$form());
}

Mujhe ye functions chahiye:
1. [Function 1 ka description]
2. [Function 2 ka description]
3. [Event ka description]

Code production-ready hona chahiye, comments Hindi mein.
```

### Specific: Validation Extension
```
Rush CLI Kodular Extension chahiye — Indian data validation ke liye.
Class: SRCValidatorUtils, Package: com.src.validatorutils
Class-level annotations nahi, sirf @SimpleFunction, @SimpleProperty, @SimpleEvent
Functions: IsValidMobile (10-digit Indian), IsValidPAN (AAAAA9999A format),
IsValidAadhaar (12-digit), IsValidGST (15-char GST), IsValidIFSC,
IsValidPincode, FormatMobile (XXXXX XXXXX), MaskAadhaar (XXXX-XXXX-1234),
GetCurrentTimestamp (DD-MM-YYYY HH:MM:SS), CapitalizeWords
Event: ValidationFailed(fieldName, value)
```

### Specific: Network Extension
```
Rush CLI Kodular Extension — Network utilities ke liye.
Class: SRCNetworkUtils, Package: com.src.networkutils
Requires permission: INTERNET, ACCESS_NETWORK_STATE
Functions: IsInternetAvailable (returns true/false),
GetNetworkType (WiFi/Mobile/None), GetIPAddress
Event: NetworkStatusChanged(isConnected, networkType)
AndroidManifest.xml mein permissions include karna
```

---

## 📌 SECTION 8 — Common Errors & Fixes

### Error 1: `rush.yml not found`
```
Karan: rush.yml file galat location par hai ya naam galat hai
Fix: rush.yml Extension folder ke root mein hona chahiye
     SRCMyExtension/rush.yml ✅ (sahi)
     SRCMyExtension/src/rush.yml ❌ (galat)
```

### Error 2: `package does not match`
```
Karan: Java file mein package aur folder path match nahi kar raha
Fix:
  Java mein: package com.src.validatorutils;
  Path:      src/com/src/validatorutils/SRCValidatorUtils.java
  Dono bilkul match hone chahiye!
```

### Error 3: `cannot find symbol: AndroidNonvisibleComponent`
```
Karan: Import missing hai
Fix: Ye import zaroor likho:
  import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
  import com.google.appinventor.components.runtime.ComponentContainer;
```

### Error 4: Build starts but .aix not found
```
Karan: Java code mein syntax error hai
Fix: Actions tab mein build logs click karo, error message padho,
     AI ko woh error paste karo aur fix maango
```

### Error 5: Rush install fail on GitHub Actions
```
Karan: GitHub Actions par Rush install script fail
Fix: build.yml mein yeh ensure karo:
  - name: Install Rush CLI
    run: |
      curl https://raw.githubusercontent.com/shreyashsaitwal/rush-cli/main/scripts/install/install.sh -fsSL | sh
      echo "$HOME/.rush/bin" >> $GITHUB_PATH
```

---

## 📌 SECTION 9 — SRC Extension List (Track Karo)

| # | Extension Name | Package | Status | Version | Description |
|---|---|---|---|---|---|
| 1 | SRCValidatorUtils | com.src.validatorutils | ✅ Ready | 1.0.0 | Indian data validation |
| 2 | (next extension) | com.src.??? | 🔲 Planned | - | - |
| 3 | | | | | |

---

## 📌 SECTION 10 — Quick Reference Card

```
┌─────────────────────────────────────────────────────────┐
│           SRC EXTENSION — QUICK REFERENCE               │
├─────────────────────────────────────────────────────────┤
│ Repository  : github.com/shreeramcomputers/src-extensions│
│ Language    : Java (Java 8 compatible)                  │
│ Build Tool  : Rush CLI (auto-install)                   │
│ JDK         : 11 (GitHub Actions par)                   │
│ Min SDK     : 21 (Android 5.0)                          │
│ Output      : out/*.aix file                            │
├─────────────────────────────────────────────────────────┤
│ CLASS-LEVEL  : rush.yml mein (annotation NAHI lagata)   │
│ @SimpleFunction  : ✅ Java mein lagate hain             │
│ @SimpleProperty  : ✅ Java mein lagate hain             │
│ @SimpleEvent     : ✅ Java mein lagate hain             │
├─────────────────────────────────────────────────────────┤
│ Package Format : com.src.<extensionname>                 │
│ Java Path      : src/com/src/<name>/<Class>.java         │
├─────────────────────────────────────────────────────────┤
│ BUILD COMMAND  : rush build --release                    │
│ ARTIFACT       : Actions → Artifacts → .aix download    │
│ IMPORT         : Kodular → Extensions → Import .aix      │
└─────────────────────────────────────────────────────────┘
```

---

## 📌 SECTION 11 — Important Links

| Resource | URL |
|---|---|
| SRC Extensions Repo | https://github.com/shreeramcomputers/src-extensions |
| Rush CLI Official | https://github.com/shreyashsaitwal/rush-cli |
| Rush Wiki — rush.yml | https://github.com/shreyashsaitwal/rush-cli/wiki/Metadata-File-(rush.yml) |
| Rush Wiki — Structure | https://github.com/shreyashsaitwal/rush-cli/wiki/Project-Structure |
| Rush Wiki — Build | https://github.com/shreyashsaitwal/rush-cli/wiki/Build-Command |
| Rush Wiki — Manifest | https://github.com/shreyashsaitwal/rush-cli/wiki/Android-Manifest-File |
| Kodular Community | https://community.kodular.io |
| GitHub Actions Docs | https://docs.github.com/en/actions |
| AI Help | https://claude.ai |

---

## 📌 SECTION 12 — SRC Business Info (Extension Branding)

```
Business     : Shree Ram Computers (SRC)
Owner        : Gaurav Jain
Location     : Daurala, Meerut, Uttar Pradesh
Website      : shreeramcomputers.com
Mobile       : 9557283429
Email        : support@shreeramcomputers.com
```

**Extension branding ke liye yeh description use karo:**
```
"Extension by Shree Ram Computers (SRC), Daurala, Meerut, UP.
For support visit shreeramcomputers.com"
```

---

*Document Version: 1.0 | Date: May 2026 | Prepared by: AI Assistant (Claude)*
*Based on: Rush CLI Official Documentation (github.com/shreyashsaitwal/rush-cli/wiki)*
