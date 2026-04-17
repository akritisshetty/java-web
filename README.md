# Portfolio – Personal Website  
**Akriti S Shetty · Data Analyst & ML Engineer**

> A clean, dark-mode personal portfolio built with **Java 21**, **Spring Boot 3** and **Maven** — CI/CD-ready with **Jenkins**.

---

## Overview

This is an extensible personal portfolio website that displays:

| Section | Content |
|---|---|
| **Hero** | Profile photo, name, title, short bio |
| **Contact** | Email, phone, location, LinkedIn, GitHub |
| **Education** | Degree, board results |
| **Experience** | Internship roles with bullet descriptions |
| **Skills** | Categorised skill tags |
| **Resume** | One-click PDF download buttons |

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.4.4 |
| Build tool | Apache Maven 3.8+ |
| Styling | Vanilla CSS (dark mode, glassmorphism) |
| CI/CD | Jenkins (Declarative Pipeline) |
| IDE | Eclipse (or any JDK 21–compatible IDE) |

---

## Project Structure

```
java-web/
├── src/
│   ├── main/
│   │   ├── java/com/example/portfolio/
│   │   │   ├── PortfolioApplication.java
│   │   │   └── controller/
│   │   │       └── PortfolioController.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   ├── css/style.css
│   │       │   ├── img/me.jpeg
│   │       │   └── resume/
│   │       │       ├── Akriti_S_Shetty_Data_Analyst.pdf
│   │       │       └── Akriti_S_Shetty.pdf
│   │       └── templates/
│   │           └── index.html
│   └── test/
│       └── java/com/example/portfolio/
│           └── PortfolioApplicationTests.java
│
├── Jenkinsfile
├── pom.xml
└── README.md
```

---

## Prerequisites

| Tool | Minimum version |
|---|---|
| JDK | 21 |
| Maven | 3.8 |
| Git | any |
| Eclipse | 2023-09 or newer (with Spring Tools optional) |
| Jenkins | 2.440+ (optional, for CI/CD) |

---

## Quick Start (Terminal)

```bash
# 1. Navigate to the project root
cd /path/to/java-web

# 2. Build (compiles, runs tests, packages fat-JAR)
mvn clean install

# 3. Run
java -jar target/portfolio-1.0.0.jar

# 4. Open in browser
xdg-open http://localhost:8080
```

> **Hot reload**: With `spring-boot-devtools` on the classpath, run via  
> `mvn spring-boot:run` and the server restarts automatically when you save a file.

---

## Import into Eclipse

1. Open Eclipse → **File ▸ Import ▸ Maven ▸ Existing Maven Projects**.  
2. Set the **Root Directory** to this project folder.  
3. Tick `pom.xml` → **Finish**.  
4. Wait for Maven to download dependencies (progress bar, bottom-right).  
5. Right-click `PortfolioApplication.java` → **Run As ▸ Spring Boot App** (or **Java Application**).  
6. Open `http://localhost:8080` in your browser.

---

## Jenkins CI/CD Setup

### First-time configuration

1. Install the **Pipeline** and **Git** plugins in Jenkins.  
2. Create a new **Pipeline** job.  
3. Under *Pipeline Definition* choose **Pipeline script from SCM**.  
4. Set SCM to **Git**, paste your repository URL.  
5. Set *Script Path* to `Jenkinsfile`.  
6. Save and click **Build Now**.

### Pipeline stages

| # | Stage | What it does |
|---|---|---|
| 1 | **Checkout** | Clones the repository |
| 2 | **Verify Environment** | Prints Java & Maven version |
| 3 | **Build** | `mvn clean install`, publishes JUnit results |
| 4 | **Archive** | Saves the fat-JAR as a Jenkins artefact |
| 5 | **Run Application** | Kills any old instance, starts the app, health-checks it |

---

## GitHub Integration

```bash
# Step 1 – Initialise (run once, inside the project root)
git init
git branch -M main

# Step 2 – Create a .gitignore
cat > .gitignore << 'EOF'
target/
*.class
*.jar
.settings/
.project
.classpath
.idea/
*.iml
EOF

# Step 3 – Stage everything
git add .
git commit -m "feat: initial portfolio Spring Boot project"

# Step 4 – Add remote & push
git remote add origin https://github.com/<your-username>/<repo-name>.git
git push -u origin main
```

---

## Adding / Updating Assets

| Asset | Where to place it |
|---|---|
| New profile photo | `src/main/resources/static/img/` |
| New / updated resume | `src/main/resources/static/resume/` |

---
