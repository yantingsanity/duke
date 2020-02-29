# User Guide

## 1. Introduction
Ting's duke is an application for those who <b>prefer to use a desktop app for managing tasks</b>. More importantly, it is <b>optimised for those who prefer to work with a Command Line Interface (CLI) </b>. Would you like to start playing with this new task manager? :smiles:
## 2. Features 

### 2.1 Listing the tasks: `list`
This lists out the current tasks that you have inserted into the application.

#### Usage

#### `list` - lists out the tasks

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list:`<br>
 `1. [E][✗] hello world  (at: Jan 1 2020)`<br>
 `2. [T][✓] math homework`<br>
 `3. [D][✗] essay  (by: Jan 2 2019)`

### 2.2 Marking a task as done: `done`
This allows you to mark a task as completed once you have finished it.

#### Usage

#### `done` - mark a task as done

Use `list` to view the list of the tasks and choose the number for the task that you have completed by doing this `done <INDEX>`.

Example of usage: 

`list`<br>
`done 2`

Expected outcome:

`Here are the tasks in your list:`<br>
 `1. [E][✗] hello world  (at: Jan 1 2020)`<br>
 `2. [T][✗] math homework`<br>
 `3. [D][✗] essay  (by: Jan 2 2019)`

After listing as shown above, mark the second item on the list as done and the following will be shown: <br>
`Nice! I have marked this task as done:`<br>
 `[T][✓] math homework ` <br>

### 2.3 Delete task from the list: `delete`
This allows you to delete a task from the list.
#### Usage
 
#### `delete` - delete the specified task
 
 Use `list` to view the list of the tasks and choose the number for the task that you want to delete by doing this `delete <INDEX>`.
 
 Example of usage: 
 
 `list` <br>
 `delete 1`
 
 Expected outcome:
 
`Here are the tasks in your list:`<br>
 `1. [E][✗] hello world  (at: Jan 1 2020)`<br>
 `2. [T][✓] math homework`<br>
 `3. [D][✗] essay  (by: Jan 2 2019)`
 
 
After listing as shown above, first item will be deleted and the following will be shown: <br>
`[E][✓] hello world  (at: Jan 1 2020) has been deleted!`<br>
`Now you have 2 tasks in the list.`
  
### 2.4 Finding a task: `find`
This allows you to find a specific task from the list.
#### Usage
 
#### `find` - finding a specific task from the list
 
Use `find` to find the task.

 Example of usage: 
 
 `find essay`
 
 Expected outcome:
 
 `1. [D][✓] essay  (by: Jan 2 2019 12:00)`
 
### 2.5 Adding tasks into the application
In this Ting's duke, you will be able add three different type of tasks into the tasks list.  

#### 2.5.1 Adding a ToDo task: `todo`
This allows you to add in a ToDo task. A ToDo task only consists of task description.

#### Usage
 
#### `todo TASK_NAME` - add in a ToDo task
 
Use `todo TASK_NAME` to add in a task

 Example of usage: 
 
 `todo math homework`
 
 Expected outcome:
 
`Got it. I've added this task:`<br>
 `[T][✗] math homework`<br>
 `Now you have 7 tasks in the list.`
 
#### 2.5.2 Adding a Deadline task: `deadline`
This allows you to add in a Deadline task. A Deadline task consists of task description, date and time that the task need to be submitted.

#### Usage
 
#### `deadline TASK_NAME /by TASK_DATE [TASK_TIME]` - add in a Deadline task
Use `deadline TASK_NAME /by TASK_DATE [TASK_TIME]` to add in a task

 TASK_NAME: name of the task<br>
 TASK_DATE: date by which the task needs to be submitted in this format of `yyyy-mm-dd` <br>
 TASK_TIME: (optional) time by which the task needs to be submitted in this format `hh:mm`<br>

 Example of usage: 
 
 `deadline CS2113T iP week 6 /by 2020-02-02 13:00`
 
 Expected outcome:
 
`Got it. I've added this task:`<br>
`[D][✗] CS2113T iP week 6  (by: Feb 2 2020 13:00)`<br>
`Now you have 8 tasks in the list.`
 
#### 2.5.3 Adding an Event task: `event`
This allows you to add in an Event task. An Event task consists of event description, date and time of the event..

#### Usage
 
#### `event EVENT_NAME /at EVENT_DATE [EVENT_TIME]` - add in an Event task
Use `event EVENT_NAME /at EVENT_DATE [EVENT_TIME]` to add in an Event task

 EVENT_NAME: name of the event<br>
 EVENT_DATE: date of the event in this format of `yyyy-mm-dd` <br>
 EVENT_TIME: (optional) time of the event in this format `hh:mm`<br>

 Example of usage: 
 
 `event Colours' Run /at 2019-11-02 09:00`
 
 Expected outcome:
`Got it. I've added this task:`<br>
 `[E][✗] Colours' Run  (at: Nov 2 2019 09:00)`<br>
 `Now you have 9 tasks in the list.`<br>
 
### 2.6 End the program: `bye`
This will terminate the program.
#### Usage
 
#### `bye` - end the program
 
 Use `bye` to terminate the program.
 
 Expected outcome:
 
 `BYE BYE SEE YOU SOON!`
 
