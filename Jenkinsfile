pipeline {
    agent any

    // ─────────────────────────────────────────────────────────────
    // Global environment variables
    // ─────────────────────────────────────────────────────────────
    environment {
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
        APP_PORT  = '8080'
        JAR_NAME  = 'portfolio-1.0.0.jar'
    }

    // ─────────────────────────────────────────────────────────────
    // Build options
    // ─────────────────────────────────────────────────────────────
    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 20, unit: 'MINUTES')
    }

    stages {

        // ── 1. Clone / Checkout ───────────────────────────────────
        stage('Checkout') {
            steps {
                echo '==> Checking out source code from GitHub …'
                checkout scm
            }
        }

        // ── 2. Verify Tooling ─────────────────────────────────────
        stage('Verify Environment') {
            steps {
                echo '==> Verifying Java and Maven …'
                sh 'java -version'
                sh 'mvn  --version'
            }
        }

        // ── 3. Build & Test ───────────────────────────────────────
        stage('Build') {
            steps {
                echo '==> Running mvn clean install …'
                sh 'mvn clean install -B -Dmaven.test.failure.ignore=true'
            }
            post {
                always {
                    junit allowEmptyResults: true,
                          testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        // ── 4. Archive Artefact ───────────────────────────────────
        stage('Archive') {
            steps {
                echo '==> Archiving the JAR …'
                archiveArtifacts artifacts: "target/${env.JAR_NAME}",
                                 fingerprint: true,
                                 allowEmptyArchive: false
            }
        }

        // ── 5. Deploy / Run ───────────────────────────────────────
        stage('Run Application') {
            steps {
                echo '==> Starting the Spring Boot application …'
                // Kill any existing instance on port 8080 before restarting
                sh """
                    PID=\$(lsof -ti tcp:${env.APP_PORT} || true)
                    if [ -n "\$PID" ]; then
                        echo "Killing existing process on port ${env.APP_PORT} (PID \$PID)"
                        kill -9 \$PID
                    fi
                    nohup java -jar target/${env.JAR_NAME} \
                        --server.port=${env.APP_PORT} \
                        > app.log 2>&1 &
                    echo "Application started. Waiting for it to be ready …"
                    sleep 15
                    curl -fs http://localhost:${env.APP_PORT}/ | grep -q 'Akriti' \
                        && echo "==> Application is UP at http://localhost:${env.APP_PORT}/" \
                        || (echo "==> Health-check failed!" && exit 1)
                """
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Post-build notifications
    // ─────────────────────────────────────────────────────────────
    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check the logs above.'
        }
        always {
            cleanWs()
        }
    }
}
