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
7. Verify the following message in the popup "Your message was sent successfully"

ID:One click order test 1-2.User select wathces and click "One click order" enter only field "Your Name" and see "Please fill out this field."

1. Launch site "https://luch.by/en/"
2. Click "One click order" on the any watches
3. Enter value "Dzmitrytest" to field "Your Name"
4. Click "Send form"
5. Verify the following message in the popup "Please fill out this field."

ID:One click order test 1-3.User select wathces and click "One click order" enter only field "Phone" and see "Please fill out this field."

1. Launch site "https://luch.by/en/"
2. Click "One click order" on the any watches
3. Enter value "testphonenumber" to field "Phone"
4. Click "Send form"
5. Verify the following message in the popup "Please fill out this field."

ID:Product test 2-1.User enter product page "https://luch.by/en/kollektsii/defender/77431556/" and click "Add to cart"

1. Launch site "https://luch.by/en/kollektsii/defender/77431556/"
2. Click "Add to cart"
3. Click to 'checkout' on product page
4. Verify if the product in cart

ID:Product test 2-2.User enter product page and add product to cart, then add one more quantity 
1. Launch site "https://luch.by/en/kollektsii/defender/77431556/"
2. Click "Add to cart"
3. Click to "checkout" on product page
4. Click "+" to add one more quantity
5. Verify the price has doubled

ID:Product test 2-3.User enter product page and add product to cart, then delete product

1. Launch site "https://luch.by/en/kollektsii/defender/77431556/"
2. Click "Add to cart"
3. Click to 'checkout' on product page
4. Click to "Delete" button
5. Verify if the cart is empty

ID:Product test 2-4.User twice enter product page and add product to cart, then verify total price
1.Repeat steps on Product test 2-1 with "/en/kollektsii/defender/77431556/"
2.Repeat steps on Product test 2-1 with "/en/kollektsii/obratnyy-khod/272081648/"
3.Verify the total price is sum price of both product's

ID:Order test 3-1.User order a product is correctly

1. Repeat steps on Product test 2-1
2. Enter value "Dzmitrytest" to field "Name and Surname"
3. Enter value "testphonenumber" to field "Telephone"
4. Enter value "test@test.by*" to field "E-mail"
5. Enter value "Minsk" to field "City"
6. Select "Pickup in Minsk" on the "Delivery service"
7. Select "Credit card & Apple Pay" on the "Select payment System"
8. Click "Complete order"
9. Verify the payment blank is opened

ID:Order test 3-2.The user orders an item without entering all fields
1. Repeat steps on Product test 2-1
2. Click "Complete order"
3. Verify the count of following error message's in the popup is 5

ID:Order test 3-3.The user orders an item without entering the "Name and Surname" field.
1. Repeat steps on Product test 2-1
2. Enter value "testphonenumber" to field "Telephone"
3. Enter value "test@test.by*" to field "E-mail"
4. Enter value "Minsk" to field "City"
5. Click "Complete order"
6. Verify the following error message is "Контактное лицо this field is required"

ID:Order test 3-4.The user orders an item without entering the "Telephone" field.
1. Repeat steps on Product test 2-1
2. Enter value "Dzmitrytest" to field "Name and Surname"
3. Enter value "test@test.by*" to field "E-mail"
4. Enter value "Minsk" to field "City"
5. Click "Complete order"
6. Verify the following error message is "Телефон this field is required"

ID:Order test 3-5.The user orders an item without entering the "City or Country" field.
1. Repeat steps on Product test 2-1
2. Enter value "Dzmitrytest" to field "Name and Surname"
3. Enter value "testphonenumber" to field "Telephone"
4. Enter value "test@test.by*" to field "E-mail"
5. Click "Complete order"
6. Verify the following error message is "Местоположение this field is required"

ID:Order test 3-6.The user orders an item without entering the "Email" field.
1. Repeat steps on Product test 2-1
2. Enter value "Dzmitrytest" to field "Name and Surname"
3. Enter value "testphonenumber" to field "Telephone"
4. Enter value "Minsk" to field "City"
5. Click "Complete order"
6. Verify the following error message is "E-Mail this field is required"

ID:Filter test 4-1.The user choose "gender" type of watches and when switching to the product sees "gender" type.
1. Launch site "https://luch.by/en/watches/"
2. Click on the "+" button in the "Type" category filter
3. Choose "gender" type of watches.
4. Choose any watches and click "More info"
5. Verify then type of watches the "gender"

 Example: 
    | gender |
    | Women's| 
    | Men's  | 

ID:Filter test 4-2.The user choose "movementName" movement of watches and when switching to the product sees "movementName" type.
1. Launch site "https://luch.by/en/watches/"
2. Choose "movementName" movement of watches.
3. Choose any watches and click "More info"
4. Verfiy then movement of watches the "movementName"

Example: 
    | movementName |
    | Quartz       | 
    | Mechaniocal  | 

ID:Sort test 5-1.The user choose type of sort "First popular" and see "First popular" watches at the first.
1. Launch site "https://luch.by/en/watches/"
2. Click to "Sort" button
3. Choose "First popular" type of sort
4. Verfiy "First popular" watches at the first

ID:Sort test 5-2. The user choose type of sort "Price: lowest first" and see "Price: lowest first" lowest watches at the first 
1. Launch site "https://luch.by/en/watches/?sort=PRICE&order=desc"
2. Click to "Sort" button
3. Choose "Price: lowest first" type of sort
4. Verfiy "Price: lowest first" watches at the first

ID:Sort test 5-3. The user choose type of sort "Price: highest first" and see "Price: highest first" highest watches at the first 
1. Launch site "https://luch.by/en/watches/?sort=PRICE&order=asc"\
2. Verfiy "Price: highest first" watches at the first
