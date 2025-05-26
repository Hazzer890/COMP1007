# Pseudocode for COMP1007 Assignment 

---

## Astronaut.java
```
Class Astronaut:
    Variables:
        name: String
        age: int
        role: String
        nationality: String

    Constructor(name, age, role, nationality):
        Set instance variables

    Methods:
        getName(): return name
        getAge(): return age
        getRole(): return role
        getNationality(): return nationality
        setName(name): set name
        setAge(age): set age
        setRole(role): set role
        setNationality(nationality): set nationality
```

---

## Mission.java
```
Class Mission:
    Variables:
        name: String
        code: String
        destinationPlanet: String
        successRate: double
        launchYear: int
        mannedStatus: Boolean
        astronautList: GenericLinkedList

    Constructor(name, code, destinationPlanet, successRate, launchYear, mannedStatus, astronautList):
        Set instance variables

    Methods:
        edit(name, code, destinationPlanet, successRate, launchYear, mannedStatus):
            Update mission details

        Getters and setters for all variables

        getAstronaut(index):
            Return astronaut at index

        addAstronaut(astronaut):
            Add astronaut to astronautList

        removeAstronaut(astronaut):
            Remove astronaut from astronautList
```

---

## GenericLinkedList.java
```
Class GenericLinkedList:
    Variables:
        head: Node
        tail: Node
        size: int

    Class Node:
        data: T
        next: Node

    Constructor:
        Initialise head, tail, size

    Methods:
        add(data):
            Create new node
            If list empty, set head and tail
            Else, add to end and update tail
            Increment size

        remove(data):
            If list empty, return
            If head matches, remove head
            Else, traverse to find and remove node
            Update tail if needed
            Decrement size

        get(index):
            If index invalid, throw exception
            Traverse to index and return data

        getSize():
            Return size
```

---

## FileHandling.java
```
Class FileHandling:
    Method readFile(filePath, missionList):
        Open file at filePath
        For each line in file:
            If first line, skip (header)
            Split line by commas into parts
            Extract mission details from parts
            If mission is manned:
                Split astronaut data by pipe
                For each astronaut:
                    Split astronaut details by colon
                    Create Astronaut object and add to astronautList
                Create Mission with astronautList
            Else:
                Create Mission with empty astronautList
            Add Mission to missionList
        Handle IO exceptions
```

## Menu.java
```
Class Menu:
    Variables:
        scanner: Scanner
        ANSI color codes for output

    Methods:
        closer():
            Clear screen, print goodbye, close scanner

        continer():
            Prompt user to press Enter to continue

        CLIMenu():
            Clear screen, print main menu options
            Get user choice
            Print selected option
            Return choice

        viewMission(mission):
            Print mission details in color

        viewAstronaut(astronaut):
            Print astronaut details in color

        selectAstronautOption():
            Print astronaut filter options
            Loop until valid choice is made
            Return selected attribute

        selectMissionOption():
            Print mission detail options
            Get and return user choice

        stringInput(prompt):
            Prompt for string input, handle errors

        intInput(prompt):
            Prompt for integer input, handle errors

        booleanInput(prompt):
            Prompt for yes/no input, return Boolean
```

---


---

## MissionController.java
```
Class MissionController:
    main(args):
        Create missionList (GenericLinkedList)
        Create menu
        Read file into missionList
        Loop until exit:
            Show menu and handle user choice (switch statement)
            (Cases for each menu option)

    Static Methods:
        addMission(name, code, destinationPlanet, successRate, launchYear, mannedStatus, missionList, astronautList):
            Create Mission and add to missionList

        addMission(missionList, menu):
            Prompt user for mission details
            If manned, prompt for astronauts
            Add new Mission to missionList

        editMissionByCode(code, missionList, menu):
            Find mission by code
            If found, prompt for detail to edit and update

        deleteMission(mission, missionList):
            Remove mission from missionList

        getMissionByCode(code, missionList, menu):
            Find mission by code
            If found, display mission and astronauts

        viewAllMissions(missionList, menu):
            For each mission, display mission and astronauts

        viewAllMannedMissions(missionList, mannedQuery, menu):
            For each mission, if manned status matches, display

        viewSuccessProbabilities(missionList):
            Calculate and print average, highest, lowest success rates

        listAstronautsFilterByAttribute(astronautList, attribute, value, menu):
            For each astronaut, if attribute matches value, display
```

