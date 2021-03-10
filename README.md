# com.expertsoft.luch

# Run from command line: 
mvn -Dbrowser='browserName from DriverSingleton' -Denvironment='file.properties from resources' -Dsurefire.suiteXmlFiles=src\test\resources\'testsuite.xml' clean test

# Example:
mvn -Dbrowser=chrome -Denvironment=user -Dsurefire.suiteXmlFiles=src\test\resources\testng-all.xml clean test

# Test Cases:

ID:One click order test 1-1.User select wathces and click "One click order" and see "Your message was sent successfully"

1. Launch site "https://luch.by/en/"
2. Click "One click order" on the any watches
3. Enter value "Dzmitrytest" to field "Your Name"
4. Enter value "testphonenumber" to field "Phone"
5. Enter value "test@test.by" to field "E-mail"
6. Click "Send form"
7. Check popup "Your message was sent successfully"

ID:One click order test 1-2.User select wathces and click "One click order" enter only field "Your Name" and see "Please fill out this field."

1. Launch site "https://luch.by/en/"
2. Click "One click order" on the any watches
3. Enter value "Dzmitrytest" to field "Your Name"
4. Click "Send form"
5. Check popup "Please fill out this field."

ID:One click order test 1-3.User select wathces and click "One click order" enter only field "Phone" and see "Please fill out this field."

1. Launch site "https://luch.by/en/"
2. Click "One click order" on the any watches
3. Enter value "testphonenumber" to field "Phone"
4. Click "Send form"
5. Check popup "Please fill out this field."

ID:Product test 2-1.User enter product page "https://luch.by/en/kollektsii/defender/77431556/" and click "Add to cart"

1. Launch site "https://luch.by/en/kollektsii/defender/77431556/"
2. Click "Add to cart"
3. Launch cart page
4. Check is product in cart

ID:Order test 3-1.User order a product

1. Repeat steps on Product test 2-1
2. Enter value "Dzmitrytest" to field "Name and Surname"
3. Enter value "testphonenumber" to field "Telephone"
4. Enter value "test@test.by*" to field "E-mail"
5. Enter value "Minsk" to field "City"
6. Select "Pickup in Minsk" on the "Delivery service"
7. Select "Credit card & Apple Pay" on the "Select payment System"
8. Click "Complete order"
9. Check payment blank

User select watches using filter's and buys it

1. Launch site "https://luch.by/en/"
2. Click "Unisex" tab on the menu
3. Click "Quartz" on the "Movement" filter
4. Click "Mineral" on the "Glass" filter
5. Click "Sort" and use "First popular"
6. Click "More info" on the glasses
7. Click "Add to cart" and see button "Checkout"
8. Click "Checkout" and see "My shopping cart"
9. Click "Plus" on the "Basket quantity control" and see total price
10. Click "Check out" 
11. Enter value "******" to field "Name and Surname"
12. Enter value "******" to field "Telephone"
13. Enter value "******" to field "E-mail"
14. Select "Pickup in Minsk" on the "Delivery service"
15. Select "Credit card & Apple Pay" on the "Select payment System"
16. Click "Complete order"
17. Check message error "Местоположение this field is required"
18. Enter value "Minsk" on the "City or Country"
19. Click "Complete order"
20. Check payment blank


