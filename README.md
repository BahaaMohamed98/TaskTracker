# Task Tracker CLI

A command-line task management application built to practice core Java database technologies.

## 💡 Learning Goals

This project focuses on mastering fundamental Java database concepts:

- ✅ JDBC and SQL with Java
- ✅ Connection pooling with HikariCP
- ✅ In-memory and file-based databases using H2
- ✅ Clean project structure (DAO pattern, Mapper classes)
- ✅ Unit testing with JUnit 5
- ✅ CLI development with PicoCLI

The goal is to build solid foundations in Java database access (JDBC) before transitioning to frameworks like JPA or
Spring Boot.

## 🛠 Tech Stack

- **Java 21** - Programming language
- **H2 Database** - Embedded SQL database
- **HikariCP** - High-performance connection pooling
- **PicoCLI** - Command-line interface framework
- **JUnit 5** - Testing framework
- **Gradle** - Build automation (Kotlin DSL)

## 🚀 Building and Running

### Prerequisites

- Java 21 or higher
- No additional setup required (Gradle wrapper included)

### Build the Application

```bash
./gradlew build
```

### Setup (Recommended)

```bash
# Make the script executable
chmod +x task-cli
```

### Tab Completion (Optional)

```bash
# Generate the completion script
./gradlew generateCompletion

# Source the completion script
source task-cli_completion
```

> 💡 **Tip**: With tab completion enabled, press TAB after typing a partial command to see available options.

### Usage Examples

#### General Commands

```bash
# Show help
./task-cli --help

# Show version
./task-cli --version
```

> ℹ️ **Tip**: You can use `--help` with any subcommand (e.g., `./task-cli add --help`) to see detailed options.

#### Adding a new task

```bash
./task-cli add "Learn JDBC" -d "Practice database operations"
```

#### Updating and deleting tasks

```bash
# Update task title
./task-cli update 1 -t "Master JDBC fundamentals"

# Update task description
./task-cli update 1 -d "Complete all JDBC exercises"

# Update both title and description
./task-cli update 1 -t "New Title" -d "New Description"

# Delete a task
./task-cli delete 1
```

#### Marking a task's status

```bash
# Mark task as done
./task-cli mark done 1

# Mark task as todo (reopen)
./task-cli mark todo 1
```

#### Listing Tasks

```bash
# List all tasks
./task-cli list

# List only completed tasks
./task-cli list done

# List only pending tasks  
./task-cli list todo
```

### Alternative: Run with Gradle

If you prefer not to use the script:

```bash
./gradlew run --args="add 'Task title'"
```
