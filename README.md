# Onboarding for FRC-9430/2025-OffSeason

Welcome to the `2025-OffSeason` project! This document is a guide for our team and interested parties to understand our team's goals, conventions, and technical landscape. It is regularly reviewed and updated accordingly to stay up to date.

## 1. Project Overview

This repository contains the Java code for our FRC robot for the 2025 offseason. The primary goal is to practice developing a prototype robot based on familiar systems we've worked with in the past to onboard rookie programmers. Meanwhile, veteran programmers are assigned to tasks with more advanced concepts and designs.

## 2. Team Information & Best Practices

### Development Philosophy

We work in a distributed, collaborative environment with opportunities to work in person around
fellow students and mentors and at home. Our team prioritizes clean, documented, readable and
maintainable code. In the off season, we focus on building familiarity with our team's repository,
our organization project standards and workflows, testing improvements to prior year designs, and prototyping new ideas ahead of the competition season. We strive to practice test-driven development, but this methodology is struggling to gain traction due to inexperience and time pressures.

### Git Workflow
We work with Git in a typical feature-branch strategy based largely on feature requests submitted to our code's associated project on GitHub. We prioritize isolated feature based development to ensure subsystems can be tested on their own without unmet prerequisites. Commit messages should be written imperatively with the summaries under 80 characters. Commits should be atomic and effort-based (each commit should pertain to a specific effort). Complex commits, breaking fix commits, experimental or incomplete commits, and dirty commits should contain detailed information in addition to a summary. Work for features with no existing requirements or dependencies must be branched from `main`, with future identified requirements to be merged in as needed. PRs require reviews from at least one veteran and one rookie OR one mentor before merging. All features must pass all stated acceptance criteria before being marked as "Completed".

### Coding Conventions
We generally follow Google Java Style Guide, although some conventions may deviate based on existing FRC designs. We prefer four-space indents over tab indents. Constants must be defined in `Constants.java` separated by class relating to the overall theme of the constants group. Subsystems must be developed as independently as possible unless there are strict dependencies for subsystem operation. All functions must have docblocks specifying a synopsis of the function, detailed information for each parameter, and details about the returned object if applicable. As much as possible, logic and calculations should be separated from execution statements as much as possible (ie. we prefer defining a calculated value before passing it in as a parameter to a function).

### Team Strengths & Weaknesses
*   **Strengths**: *To be filled in by students*
*   **Weaknesses**: *To be filled in by students*

---

## 3. Technical Landscape

This section contains information derived from the current project structure. Please review and update this section regularly to avoid inconsistencies.

*   **Language**: Java
*   **Build System**: Gradle
*   **Framework**: WPILib Command-Based Robot
*   **IDE**: Visual Studio Code

### Key Subsystems & Components

This section summarizes each subsystem and its purpose.

*   **Drivetrain**: Swerve Drive, WCP Swerve x2t modules with NEO Vortex motors and SparkMax motor controllers.
*   **Localization**: Feature in development. Planned to integrate Phoenix6 IMU with Vision using a field object as an absolute reference point.
*   **Vision**: AprilTag detection using PhotonVision.
*   **Autonomous**: PathPlanner is used for creating and following autonomous paths.

### Subsystem Dependencies
*List required dependencies for interconnected subsystems as applicable. Be sure to note any isolated subsystems (ie. no dependencies) as well.*

### Vendor Libraries
*   **CTRE**: Phoenix 5 & Phoenix 6
*   **REV Robotics**: REVLib (for SPARK motor controllers and sensors)
*   **PathPlannerLib**: For autonomous pathing
*   **PhotonLib**: For vision processing
