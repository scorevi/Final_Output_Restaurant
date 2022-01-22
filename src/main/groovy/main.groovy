class Globals {

    static def username // input user
    static def password // input password
    static def userRole // determining user's privileges
    static def loginState = false // if user's logged in or out
    static def optionSelected // determining index of selected choices

}

// Global variables are located at 'Globals.groovy' file.
FileChecking()
restoLogin()

// C O R E  F U N C T I O N S

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
    println("4. Log out") // Member 1 - Sean (FINISHED)
    println("5. Exit") // Member 1 - Sean (FINISHED)

    println("")

    if (Globals.userRole == "Administrator") {

        println("---Administrative Stuff---")
        println("6. Add Restaurant") // Member 1 - Sean (FINISHED)
        println("7. Add Location") // Member 2 - Sean (FINISHED)
        println("8. Remove Restaurant") // Member 1 - Sean (FINISHED)

        println("9. Remove Location") // Member 2 - Pat (STATUS UNKNOWN)
        println("10. Add Category") // Member 2 - Pat (STATUS UNKNOWN)
        println("11. Remove Category") // Member 2 - Pat (STATUS UNKNOWN)

    }

    println("")

    print("Input: ")

    Globals.optionSelected = System.in.newReader().readLine()

    switch (Globals.userRole) { // Checking user privileges

        case "Administrator":
            switch (Globals.optionSelected) {
                case "6":
                    AddRestaurant()
                    break
                case "7":
                    AddLocation()
                    break
                case "8":
                    RemoveRestaurant()
                    break
                case "9":
                    RemoveLocation()
                    break
                case "10":
                    AddCategory()
                    break
                case "11":
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
                case "5":
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

// User Functions ---

void SearchRestaurants() {
    String userInput
    def userInputCmd
    def restoActionFinished = false

    println("--Search Restaurants--")
    println("Type the name of a location, restaurant or category to search. If you want to cancel, type 'exit program'.")
    print("Search: ")

    userInput = System.in.newReader().readLine()
    userInput = userInput.toLowerCase()

    def locFile = new File(System.getProperty("user.home") + '/RestoFinder/restaurants.txt')


    String[] SearchLines = locFile.text.toLowerCase().split("\r\n")

    List<String> SearchLinesNames = new ArrayList<String>()
    List<String> SearchLinesLocations = new ArrayList<String>()
    List<String> SearchLinesCategories = new ArrayList<String>()
    List<String> MatchingRestaurantList = new ArrayList<String>()

    boolean isRestoNameFound
    boolean isRestoLocationFound
    boolean isRestoCategoryFound

    for (String str in SearchLines) {
        int count = 0
        if (str.containsIgnoreCase(userInput)) {

            for (String s in str.split(" \\| ")) {
                count += 1

                switch (count) {
                    case 1:
                        SearchLinesNames.add(s)
                        if (SearchLinesNames.stream().anyMatch(a -> a.contains(userInput))) {
                            MatchingRestaurantList.add(str)
                            isRestoNameFound = true
                        }
                        break
                    case 2:
                        SearchLinesLocations.add(s)
                        if (SearchLinesLocations.stream().anyMatch(a -> a.contains(userInput))) {
                            MatchingRestaurantList.add(str)
                            isRestoLocationFound = true
                        }
                        break
                    case 3:
                        SearchLinesCategories.add(s)
                        if (SearchLinesCategories.stream().anyMatch(a -> a.contains(userInput))) {
                            MatchingRestaurantList.add(str)
                            isRestoCategoryFound = true
                        }
                        break
                }
            }

        }
    }


    if (isRestoNameFound == true || isRestoLocationFound == true || isRestoCategoryFound == true) {

        println("Name - Location - Category - Address")
        println("")
        BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.home") + '/RestoFinder/restaurants.txt'))
        int linescount = 0

        while (reader.readLine() != null) {
            if (isRestoNameFound == true || isRestoLocationFound == true || isRestoCategoryFound == true) {
                linescount++
            }

        }
        reader.close();
        if (isRestoNameFound == true || isRestoLocationFound == true || isRestoCategoryFound == true) {
            for (int i = 1; i <= MatchingRestaurantList.size(); i++) { // Display restaurant list
                def line = MatchingRestaurantList.get(i - 1)
                println(i + ". " + line)
            }
        }

    } else if (userInput.contains("exit program")) {
        println("Returning to main menu...")
        sleep(2000)
        initializeMainMenu()
    } else {
        println("Found nothing.")
    }

    while (!restoActionFinished) {

        println("")
        println("---Actions---")
        println("1. Return to menu")
        println("Type the number of an option to continue.")
        println("")

        print("Selection: ")
        userInputCmd = System.in.newReader().readLine()

        switch (userInputCmd) {
            case "1":
                restoActionFinished = true
                initializeMainMenu()
                break
            default:
                println("Invalid input, please try again!")
                break
        }

    }


}

// V I E W I N G  F U N C T I O N S

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


// A D M I N I S T R A T I V E  F U N C T I O N S

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
        String[] locationlines = lines_location.toLowerCase().split("\n")
        boolean isLLFound = locationlines.any { restoLocation.contains(it) }

        if (isLLFound) {
            print("Category: ")
            restoCategory = System.in.newReader().readLine()

            while (!restoActionFinished) {
                restoCategory = restoCategory.toLowerCase()
                def lines_category = new File(System.getProperty("user.home") + '/RestoFinder/categories.txt').text
                String[] categorylines = lines_category.toLowerCase().split("\n")
                boolean isCLFound = categorylines.any { restoCategory.contains(it) }
                if (isCLFound) {

                    print("Address: ")
                    restoAddress = System.in.newReader().readLine()
                    while (!restoActionFinished) {
                        if (!restoAddress.isAllWhitespace()) {

                            // Add data to their respective files
                            new File(System.getProperty("user.home") + '/RestoFinder/restaurants.txt').withWriter('utf-8') {
                                writer -> writer.writeLine(restoName + ' | ' + restoLocation + ' | ' + restoCategory + ' | ' + restoAddress)
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
    restoLocationName = restoLocationName.toLowerCase()
    while (!restoActionFinished) {
        if (restoLocationName.isBlank()) {
            println("Invalid input! You entered nothing, please try again!")
            print("Location: ")
            restoLocationName = System.in.newReader().readLine()
            restoLocationName = restoLocationName.toLowerCase()
        } else {
            String[] locationlines = lines_location.toLowerCase().split("\r\n")
            boolean isLLFound = locationlines.any { restoLocationName.contains(it) }

            if (isLLFound) {
                println("This location name already exists on the file, please try again!")
                print("Location: ")
                restoLocationName = System.in.newReader().readLine()
                restoLocationName = restoLocationName.toLowerCase()
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
                replacer.call(NewFile, '\\|', '') // Remove extra spaces
                replacer.call(NewFile, '\\s', '') // Remove extra spaces
                oldFile.delete()
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/restaurants.txt')
            } else if ((userInput as int).equals(linescount)) {
                NewFile.withWriter { writer -> writer.write(oldFile.text) }
                replacer.call(NewFile, fileLines.get(userInput as int - 1), "") // Remove string
                replacer.call(NewFile, '\\|', '') // Remove extra spaces
                replacer.call(NewFile, '\\s+$', '') // Remove extra spaces
                oldFile.delete()
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/restaurants.txt')
            } else {
                NewFile.withWriter { writer -> writer.write(oldFile.text) }
                replacer.call(NewFile, fileLines.get(userInput as int - 1), "") // Remove string
                replacer.call(NewFile, '\\|', '') // Remove extra spaces
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
    def userInput
    def oldFile = new File(System.getProperty("user.home") + '/RestoFinder/locations.txt')
    def NewFile = new File(System.getProperty("user.home") + '/RestoFinder/locations-temp.txt')

    BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.home") + '/RestoFinder/locations.txt'))
    int linescount = 0
    List<String> fileLines = oldFile.readLines()

    while (reader.readLine() != null) {
        linescount++
    }
    reader.close();

    println("Remove a location to exclude from the search operation by typing its respective number.")
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
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/locations.txt')
            } else if ((userInput as int).equals(linescount)) {
                NewFile.withWriter { writer -> writer.write(oldFile.text) }
                replacer.call(NewFile, fileLines.get(userInput as int - 1), "") // Remove string
                replacer.call(NewFile, '\\s+$', '') // Remove extra spaces
                oldFile.delete()
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/locations.txt')
            } else {
                NewFile.withWriter { writer -> writer.write(oldFile.text) }
                replacer.call(NewFile, fileLines.get(userInput as int - 1), "") // Remove string
                replacer.call(NewFile, '\\s+', '\n') // Remove extra spaces
                oldFile.delete()
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/locations.txt')
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
                RemoveLocation()
            }
        }

    } else {
        println("Invalid input, please try again!")
        RemoveLocation()
    }
}

void AddCategory() {
    String restoCategoryName
    boolean restoActionFinished
    def lines_category = new File(System.getProperty("user.home") + '/RestoFinder/categories.txt').text

    println("--Add Category--")
    print("Category: ")
    restoCategoryName = System.in.newReader().readLine()
    restoCategoryName = restoCategoryName.toLowerCase()
    while (!restoActionFinished) {
        if (restoCategoryName.isBlank()) {
            println("Invalid input! You entered nothing, please try again!")
            print("Category: ")
            restoLocationName = System.in.newReader().readLine()
            restoCategoryName = restoCategoryName.toLowerCase()
        } else {
            String[] categorylines = lines_category.toLowerCase().split("\r\n")
            boolean isCLFound = categorylines.any { restoCategoryName.contains(it) }

            if (isCLFound) {
                println("This category name already exists on the file, please try again!")
                print("Category: ")
                restoCategoryName = System.in.newReader().readLine()
                restoCategoryName = restoCategoryName.toLowerCase()
            }
            restoActionFinished = true
            restoCategoryName = restoCategoryName.toLowerCase()
            new File(System.getProperty("user.home") + '/RestoFinder/categories.txt').append(restoCategoryName + '\n')

            println("Successfully added '" + restoCategoryName + "' category!")
            sleep(5000)
            initializeMainMenu()
        }
    }
}

void RemoveCategory() {
    def userInput
    def oldFile = new File(System.getProperty("user.home") + '/RestoFinder/categories.txt')
    def NewFile = new File(System.getProperty("user.home") + '/RestoFinder/categories-temp.txt')

    BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.home") + '/RestoFinder/categories.txt'))
    int linescount = 0
    List<String> fileLines = oldFile.readLines()

    while (reader.readLine() != null) {
        linescount++
    }
    reader.close();

    println("Remove a category to exclude from the search operation by typing its respective number.")
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
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/categories.txt')
            } else if ((userInput as int).equals(linescount)) {
                NewFile.withWriter { writer -> writer.write(oldFile.text) }
                replacer.call(NewFile, fileLines.get(userInput as int - 1), "") // Remove string
                replacer.call(NewFile, '\\s+$', '') // Remove extra spaces
                oldFile.delete()
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/categories.txt')
            } else {
                NewFile.withWriter { writer -> writer.write(oldFile.text) }
                replacer.call(NewFile, fileLines.get(userInput as int - 1), "") // Remove string
                replacer.call(NewFile, '\\s+', '\n') // Remove extra spaces
                oldFile.delete()
                NewFile.renameTo(System.getProperty("user.home") + '/RestoFinder/categories.txt')
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
                RemoveCategory()
            }
        }

    } else {
        println("Invalid input, please try again!")
        RemoveCategory()
    }
}
//