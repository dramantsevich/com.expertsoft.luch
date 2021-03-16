# com.expertsoft.luch

# Run from command line:
mvn -Dbrowser='browserName from DriverSingleton' -Denvironment='file.properties from resources' -Dsurefire.suiteXmlFiles=src\test\resources\'testsuite.xml' clean test

# Example:
mvn -Dbrowser=chrome -Denvironment=user -Dsurefire.suiteXmlFiles=src\test\resources\testng-parallel.xml clean test

# To run allure-report:
to command line: allure -serve allure-results

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

ID:Order test 3-1.User order a product is correctly

1. Repeat steps on Product test 2-1
2. Enter value "Dzmitrytest" to field "Name and Surname"
3. Enter value "testphonenumber" to field "Telephone"
4. Enter value "test@test.by*" to field "E-mail"
5. Enter value "Minsk" to field "City"
6. Select "Pickup in Minsk" on the "Delivery service"
7. Select "Credit card & Apple Pay" on the "Select payment System"
8. Click "Complete order"
9. Check payment blank

ID:Order test 3-2.The user orders an item without entering all fields
1. Repeat steps on Product test 2-1
2. Click "Complete order"
3. See 5 error message's

ID:Order test 3-3.The user orders an item without entering the "Name and Surname" field.
1. Repeat steps on Product test 2-1
2. Enter value "testphonenumber" to field "Telephone"
3. Enter value "test@test.by*" to field "E-mail"
4. Enter value "Minsk" to field "City"
5. Click "Complete order"
6. Check "Контактное лицо this field is required" error message

ID:Order test 3-4.The user orders an item without entering the "Telephone" field.
1. Repeat steps on Product test 2-1
2. Enter value "Dzmitrytest" to field "Name and Surname"
3. Enter value "test@test.by*" to field "E-mail"
4. Enter value "Minsk" to field "City"
5. Click "Complete order"
6. Check "Телефон this field is required" error message

ID:Order test 3-5.The user orders an item without entering the "City or Country" field.
1. Repeat steps on Product test 2-1
2. Enter value "Dzmitrytest" to field "Name and Surname"
3. Enter value "testphonenumber" to field "Telephone"
4. Enter value "test@test.by*" to field "E-mail"
5. Click "Complete order"
6. Check "Местоположение this field is required" error message

ID:Order test 3-6.The user orders an item without entering the "Email" field.
1. Repeat steps on Product test 2-1
2. Enter value "Dzmitrytest" to field "Name and Surname"
3. Enter value "testphonenumber" to field "Telephone"
4. Enter value "Minsk" to field "City"
5. Click "Complete order"
6. Check "E-Mail this field is required" error message 
