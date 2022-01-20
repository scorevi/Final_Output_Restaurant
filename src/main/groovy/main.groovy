// Global variables are located at 'Globals.groovy' file.
FileChecking()
restoLogin()

void FileChecking() {

    File restoFolder = new File(System.getProperty("user.home") + '/RestoFinder')
    File restoFile_Category = new File(System.getProperty("user.home") + '/RestoFinder/categories.txt')
    File restoFile_Location = new File(System.getProperty("user.home") + '/RestoFinder/locations.txt')
    File restoFile_Restaurants = new File(System.getProperty("user.home") + '/RestoFinder/restaurants.txt')


    println("Creating files on home directory, please wait...")
    println(System.getProperty("user.home"))

    println("Checking required files, please wait...")
    if (restoFolder.exists()) {
        println("Folder 'RestoFinder' found!")
    } else {
        println("Folder 'RestoFinder' does not exist, creating new folder...")
        restoFolder.mkdir()
    }
    if (restoFile_Category.exists()) {
        println('File categories.txt found!')
    } else {
        println('File categories.txt does not exist, creating new file...')
        restoFile_Category.createNewFile()
        println('File categories.txt created successfully!')
    }
    if (restoFile_Location.exists()) {
        println('File locations.txt found!')
    } else {
        println('File locations.txt does not exist, creating new file...')
        restoFile_Location.createNewFile()
        println('File locations.txt created successfully!')
    }
    if (restoFile_Restaurants.exists()) {
        println('File restaurants.txt found!')
    } else {
        println('File restaurants.txt does not exist, creating new file...')
        restoFile_Restaurants.createNewFile()
        println('File restaurants.txt created successfully!')
    }

}


void restoLogin() { // a.k.a Main Method


    println("")
    println("Login to RestoFinder")

    print("Username: ")

    Globals.username = System.in.newReader().readLine()

    print("Password: ")

    Globals.password = System.in.newReader().readLine()


    while (!Globals.loginState) {


        if (Globals.username == "admin" && Globals.password == "admin") {
            Globals.loginState = true
            Globals.userRole = "Administrator"
            println("")
            println("Login Success!")
            initializeMainMenu()

        } else if (Globals.username == "user" && Globals.password == "user") {
            Globals.loginState = true
            Globals.userRole = "User"
            println("")
            println("Login Success!")
            initializeMainMenu()

        } else {
            println("Login Failed! Incorrect Username/Password!")
            println("")

            Globals.loginState = false

            print("Username: ")
            Globals.username = System.in.newReader().readLine()
            print("Password: ")
            Globals.password = System.in.newReader().readLine()

        }

    }

}


void initializeMainMenu() {

    println("")
    println("---RestoFinder Main Menu---")
    println("Type the number of an option to continue.")

    println("1. Search Restaurants") // Member 1 - Sean (ONGOING)
    println("2. View Category List") // Member 1 - Sean (FINISHED)
    println("3. View Location List") // Member 1 - Sean (FINISHED)
    println("4. Help") // Member 1 - Sean (FINISHED)
    println("5. Log out") // Member 1 - Sean (FINISHED)
    println("6. Exit") // Member 1 - Sean (FINISHED)

    println("")

    if (Globals.userRole == "Administrator") {

        println("---Administrative Stuff---")
        println("7. Add Restaurant") // Member 1 - Sean (FINISHED)
        println("8. Add Location") // Member 2 - Sean (FINISHED)
        println("9. Remove Restaurant") // Member 1 - Sean (FINISHED)

        println("10. Remove Location") // Member 2 - Pat (STATUS UNKNOWN)
        println("11. Add Category") // Member 2 - Pat (STATUS UNKNOWN)
        println("12. Remove Category") // Member 2 - Pat (STATUS UNKNOWN)

    }

    println("")

    print("Input: ")

    Globals.optionSelected = System.in.newReader().readLine()

    switch (Globals.userRole) { // Checking user privileges

        case "Administrator":
            switch (Globals.optionSelected) {
                case "7":
                    AddRestaurant()
                    break
                case "8":
                    AddLocation()
                    break
                case "9":
                    RemoveRestaurant()
                    break
                case "10":
                    RemoveLocation()
                    break
                case "11":
                    AddCategory()
                    break
                case "12":
                    RemoveCategory()
                    break

            }

        default: // User for default case
            switch (Globals.optionSelected) {
                case "1":
                    SearchRestaurants()
                    break
                case "2":
                    ViewCategoryListFunc()
                    break
                case "3":
                    ViewLocListFunc()
                    break
                case "4":
                    break
                case "5":
                    if (Globals.loginState) {

                        Globals.loginState = false
                        Globals.username = ""
                        Globals.password = ""
                        Globals.userRole = ""

                        println("Log out successful!")
                        println("")


                        restoLogin()
                    }
                    break
                case "6":
                    System.exit(0)
                    break
                default:
                    println("Option number is invalid, please try again!")
                    sleep(5000)
                    initializeMainMenu()
                    break

            }
            break

    }

}

// Methods //

// User Functions ---

void SearchRestaurants() {
    String userInput
    def userInputCmd

    println("--Search Restaurants--")
    println("Type the name of a location, restaurant or category to search. If you want to cancel, type 'exit program'.")
    print("Search: ")
    userInput = System.in.newReader().readLine()
    userInput.toLowerCase()
    def locFile = new File(System.getProperty("user.home") + '/RestoFinder/restaurants.txt')
    List<String> fileLines = locFile.readLines()

    if (locFile.text.toLowerCase().contains(userInput.toLowerCase())) {

        println("Name - Location - Category - Address")
        println("")
        BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.home") + '/RestoFinder/restaurants.txt'))
        int linescount = 0

        while (reader.readLine() != null) {
            if (locFile.text.toLowerCase().contains(userInput.toLowerCase())) {
                linescount++
            }

        }
        reader.close();

        for (int i = 0; i < linescount; i++) { // Display restaurant list

            if (locFile.text.toLowerCase().contains(userInput.toLowerCase())) {
                def line = locFile.readLines().get(i)
                println((i + 1) + ". " + line)
            }
        }
    } else if (userInput.contains("exit program")) {
        println("Returning to main menu...")
        sleep(2000)
        initializeMainMenu()
    } else {
        println("Found nothing.")
    }

    println("")
    println("---Actions---")
    println("1. Return to menu")
    println("Type the number of an option to continue.")
    println("")

    print("Selection: ")
    userInputCmd = System.in.newReader().readLine()

    switch (userInputCmd) {
        case "1":
            initializeMainMenu()
            break
    }

}

void ViewLocListFunc() { // View restaurant locations
    def userResponse
    def lines_location = new File(System.getProperty("user.home") + '/RestoFinder/locations.txt').text
    println("")
    println("Restaurant Locations: ")

    lines_location.eachLine { line ->
        println(line)
    }

    println("")
    println("---Actions---")
    println("1. Go back")
    println("Type the number of an option to continue.")
    print("Selection: ")
    userResponse = System.in.newReader().readLine()

    switch (userResponse) {
        case "1":
            initializeMainMenu()
            break
        default:
            ViewLocListFunc()
            break
    }

}

void ViewCategoryListFunc() { // View restaurant categories
    def userResponse
    def lines_category = new File(System.getProperty("user.home") + '/RestoFinder/categories.txt').text
    println("")
    println("Restaurant Categories: ")

    lines_category.eachLine { line ->
        println(line)
    }

    println("")
    println("---Actions---")
    println("1. Go back")
    println("Type the number of an option to continue.")
    print("Selection: ")
    userResponse = System.in.newReader().readLine()

    switch (userResponse) {
        case "1":
            initializeMainMenu()
            break
        default:
            ViewCategoryListFunc()
            break
    }

}


// --- Administrator Functions ---

void AddRestaurant() {
    String restoName
    String restoLocation
    String restoCategory
    String restoAddress
    boolean restoActionFinished = false

    println("--Add Restaurant--")
    print("Name: ")
    restoName = System.in.newReader().readLine()

    print("Location: ")

    restoLocation = System.in.newReader().readLine()
    while (!restoActionFinished) {

        restoLocation = restoLocation.toLowerCase()
        def lines_location = new File(System.getProperty("user.home") + '/RestoFinder/locations.txt').text
        String[] locationlines = lines_location.toLowerCase().split("\r\n")
        boolean isLLFound = locationlines.any { restoLocation.contains(it) }

        if (isLLFound) {
            print("Category: ")
            restoCategory = System.in.newReader().readLine()

            while (!restoActionFinished) {
                restoCategory = restoCategory.toLowerCase()
                def lines_category = new File(System.getProperty("user.home") + '/RestoFinder/categories.txt').text
                String[] categorylines = lines_category.toLowerCase().split("\r\n")
                boolean isCLFound = categorylines.any { restoCategory.contains(it) }
                if (isCLFound) {

                    print("Address: ")
                    restoAddress = System.in.newReader().readLine()
                    while (!restoActionFinished) {
                        if (!restoAddress.isAllWhitespace()) {

                            // Add data to their respective files
                            new File(System.getProperty("user.home") + '/RestoFinder/restaurants.txt').withWriter('utf-8') {
                                writer -> writer.writeLine(restoName + ' - ' + restoLocation + ' - ' + restoCategory + ' - ' + restoAddress)
                            }

                            restoActionFinished = true
                            println("Successfully added restaurant to the list!")
                            sleep(5000)
                            initializeMainMenu()

                        } else {
                            println("Address field cannot be empty, please try again!")
                            println("")
                            print("Address: ")
                            restoAddress = System.in.newReader().readLine()
                        }
                    }


                } else {
                    println("Category not found in the restaurant category file. Please try again!")
                    println("")
                    println("List of valid restaurant categories: ")
                    lines_category.eachLine { line ->
                        println(line)
                    }
                    println("")
                    print("Category: ")
                    restoCategory = System.in.newReader().readLine()
                }

            }

        } else {
            println("Location not found in the location file. Please try again!")
            println("")
            println("List of valid locations: ")

            lines_location.eachLine { line ->
                println(line)
            }

            println("")
            print("Location: ")
            restoLocation = System.in.newReader().readLine()

        }

    }


}

void AddLocation() { // just add file operations here
    def restoLocationName
    def restoActionFinished
    def lines_location = new File(System.getProperty("user.home") + '/RestoFinder/locations.txt').text

    println("--Add Location--")
    print("Location: ")
    restoLocationName = System.in.newReader().readLine()

    while (!restoActionFinished) {
        if (restoLocationName.isBlank()) {
            println("Invalid input! You entered nothing, please try again!")
            print("Location: ")
            restoLocationName = System.in.newReader().readLine()
        } else {
            if (lines_location.contains(restoLocationName)) {
                println("This location name already exists on the file, please try again!")
                print("Location: ")
                restoLocationName = System.in.newReader().readLine()
            }
            restoActionFinished = true
            new File(System.getProperty("user.home") + '/RestoFinder/locations.txt').append(restoLocationName + '\n')
            println("Successfully added '" + restoLocationName + "' location!")
            sleep(5000)
            initializeMainMenu()
        }
    }

}


void RemoveRestaurant() {
    def userInput
    def oldFile = new File(System.getProperty("user.home") + '/RestoFinder/restaurants.txt')
    def NewFile = new File(System.getProperty("user.home") + '/RestoFinder/restaurants-temp.txt')

    BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.home") + '/RestoFinder/restaurants.txt'))
    int linescount = 0
    List<String> fileLines = oldFile.readLines()

    while (reader.readLine() != null) {
        linescount++
    }
    reader.close();

    println("Remove a restaurant to exclude from the search operation by typing its respective number.")
    println("If you wish to stop the operation, simply type 'cancel'.")
    println("")
    if (oldFile.text.isAllWhitespace()) {
        println("There's nothing here to show, returning to main menu...")
        sleep(5000)
        initializeMainMenu()
    }

    for (int i = 0; i < linescount; i++) { // Display restaurant list
        def line = oldFile.readLines().get(i)
        println((i + 1) + ". " + line)

    }

    println("")
    print("Selection: ")

    userInput = System.in.newReader().readLine()

    if (!userInput.isAllWhitespace()) {
        if (userInput.isNumber()) {
            NewFile.createNewFile()

            def replacer = { File source, String toSearch, String replacement ->
                source.write(source.text.replaceAll(toSearch, replacement))
            } // Create clousure for removing lines..

            if ((userInput as int).equals(1)) {
                NewFile.withWriter { writer -> writer.write(oldFile.text) }
                replacer.call(NewFile, fileLines.get(userInput as int - 1), "") // Remove string
                replacer.call(NewFile, '\\s', '') // Remove extra spaces
                oldFile.delete()
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/restaurants.txt')
            } else if ((userInput as int).equals(linescount)) {
                NewFile.withWriter { writer -> writer.write(oldFile.text) }
                replacer.call(NewFile, fileLines.get(userInput as int - 1), "") // Remove string
                replacer.call(NewFile, '\\s+$', '') // Remove extra spaces
                oldFile.delete()
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/restaurants.txt')
            } else {
                NewFile.withWriter { writer -> writer.write(oldFile.text) }
                replacer.call(NewFile, fileLines.get(userInput as int - 1), "") // Remove string
                replacer.call(NewFile, '\\s+', '\n') // Remove extra spaces
                oldFile.delete()
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/restaurants.txt')
            }


            println("Remove operation successful, returning to main menu...")
            NewFile.delete()
            sleep(5000) // Wait 5 seconds
            initializeMainMenu()

        } else {
            userInput.toLowerCase()
            if (userInput.contains('cancel')) {
                println("Remove operation canceled, returning to main menu...")
                sleep(5000)

                println("")
                initializeMainMenu()
            } else {
                println("Invalid input, please try again!")
                RemoveRestaurant()
            }
        }

    } else {
        println("Invalid input, please try again!")
        RemoveRestaurant()
    }
}

void RemoveLocation() {

}

void AddCategory() {

}

void RemoveCategory() {

}
//