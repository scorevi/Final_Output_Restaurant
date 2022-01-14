class Globals {
    static List restoNameList = []
    static List locationList = ["Balintawak", "Kamuning", "Quezon Ave."]
    static List categoryList = ["Fine Dining", "Fast Food", "Desserts"]
    static List restaurantList = [][]

    static def restoCount = 0

    static def username
    static def password
    static def userRole
    static def loginState = false
    static def optionSelected

}

restoLogin()

void restoLogin() { // a.k.a Main Method

    println("Login to RestoFinder")

    print("Username: ")

    Globals.username = System.in.newReader().readLine()

    print("Password: ")

    Globals.password = System.in.newReader().readLine()


    while (!Globals.loginState) {


        if (Globals.username == "admin" && Globals.password == "admin1234") {
            Globals.loginState = true
            Globals.userRole = "Administrator"
            println("")
            println("Login Success!")
            initializeMainMenu()

        } else if (Globals.username == "user" && Globals.password == "user1234") {
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
    println("---Main Menu---")

    println("1. Search Restaurants")
    println("2. View Restaurant List")
    println("3. View Location List")
    println("4. Help")
    println("5. Log out")

    println("")

    if (Globals.userRole == "Administrator") {

        println("---Administrative Stuff---")
        println("6. Add Restaurant")
        println("7. Add Location")
        println("8. Edit Restaurant")
        println("9. Remove Restaurant")
        println("10. Remove Location")
        println("11. Add Category")
        println("12. Remove Category")

    }

    println("")

    print("Selection: ")

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
                    EditRestaurant()
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
                    break
                case "2":
                    break
                case "3":
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

            }
            break

    }

}

// Methods //

// User Functions ---

void SearchRestaurants() {

}

void ViewListFunc() { // View restaurants and locations

}

// Administrator Functions ---

void AddRestaurant() {
    def restoName
    def restoLocation
    def restoCategory
    def restoPrice
    boolean restoActionFinished = false

    println("--Add Restaurant--")
    print("Name: ")
    restoName = System.in.newReader().readLine()

    print("Location: ")
    restoLocation = System.in.newReader().readLine()
    while (!restoActionFinished) {

        if (restoLocation in Globals.locationList) {
            print("Category: ")
            restoCategory = System.in.newReader().readLine()

            while (!restoActionFinished) {

                if (restoCategory in Globals.categoryList) {
                    println("Successfully added restaurant to the list!")

                    // Add data to their respective list array
                    Globals.restaurantList.add([Globals.restoCount][0], restoName)
                    Globals.restaurantList.add([Globals.restoCount][0], restoLocation)
                    Globals.restaurantList.add([Globals.restoCount][0], restoCategory)
                    restoActionFinished = true
                    sleep(5000)
                    initializeMainMenu()

                } else {
                    println("Category not found in the restaurant category list. Please try again!")
                    println("")
                    println("List of valid restaurant categories: ")
                    for (item in Globals.categoryList) {
                        println(item)
                    }
                    println("")
                    print("Category: ")
                    restoCategory = System.in.newReader().readLine()
                }

            }

        } else {
            println("Location not found in the location list. Please try again!")
            println("")
            println("List of valid locations: ")
            for (item in Globals.locationList) {
                println(item)
            }
            println("")
            print("Location: ")
            restoLocation = System.in.newReader().readLine()

        }

    }


}

void AddLocation() {

}

void EditRestaurant() {

}

void RemoveRestaurant() {

}

void RemoveLocation() {

}

void AddCategory() {

}

void RemoveCategory() {

}