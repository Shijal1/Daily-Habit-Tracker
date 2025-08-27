# Daily-Habit-Tracker
Daily Habit Tracker

A Java application to track daily habits, log progress, view statistics, and get reminders. Built with OOP principles, JavaFX for the GUI, and JDBC with SQLite for persistent storage.

Project Overview

Title: Daily Habit Tracker

Brief Description: A user-friendly app to manage habits (e.g., "Drink 2L water," "Study 1 hour"). Users can add habits, log completions, view progress (e.g., streaks), and receive reminders. Data is saved to a SQLite database via JDBC and displayed via a JavaFX GUI.

Problem Statement and Target Users

Problem: Maintaining consistent habits is hard without tracking or reminders. Simple tools are needed for daily routines.

Target Users: Students, professionals, or anyone building habits (e.g., exercising, reading). Ideal for students to track study goals.

OOP Concepts Used:

Encapsulation: Private fields (e.g., habit name, progress) with public getters/setters.
Inheritance: WeeklyHabit extends Habit for weekly-specific logic.
Polymorphism: Overridden methods like markComplete in subclasses.



Abstraction: HabitTracker and HabitStorage hide logic for habit management and database I/O.
