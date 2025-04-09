# 🛒 Amazon.eg Automation Project

This project automates a real-world scenario on [Amazon.eg](https://www.amazon.eg), targeting **products in the Video Games category priced below 15,000 EGP**. Using **Selenium WebDriver**, **TestNG**, and the **Page Object Model (POM)**, it adds products to the cart, calculates the total, and verifies it against the cart summary.

---

## 📌 Features

- Navigate to the **Video Games** category
- Apply **"Free Shipping"** and **"New"** filters
- Sort results by **"Price: High to Low"**
- Traverse through multiple search result pages
- Add qualifying products to the cart
- Calculate and validate the total against Amazon's cart total
- Optional **manual login** after cart addition

---

## 🧰 Technologies Used

- Java 21
- Selenium WebDriver 4.20
- TestNG 7.9
- Maven
- FirefoxDriver
- Page Object Model (POM)

---

## 🚀 Getting Started

### 1. **Clone the Repository**

```bash
git clone https://github.com/yourusername/amazon-eg-automation.git
cd amazon-eg-automation
```
### 2. **Install Maven Dependencies**
   Make sure you have Maven installed, then run:
```bash
mvn clean install
```
This will automatically download all required dependencies from pom.xml.

### 3. **Run the Test**

To execute the main test:
```bash
mvn test
```

The test will:

- Search Amazon.eg

- Add valid items to the cart

- Navigate to the cart

- Compare calculated total with cart total

- Manual Login (Optional)

### **If you wish to log in:**

The login steps are included but commented out in the code

You may uncomment and update the credentials locally

OTP must be entered manually within the allotted time (10 seconds)

**⚠️ Credentials are not stored or shared for security reasons**

## **Prerequisites**

- Java 17+ (Project uses Java 21)

- Maven 3.8+

- Firefox browser



### **Author**

Manal Sewaied

Software QA Engineer

[Linked in]