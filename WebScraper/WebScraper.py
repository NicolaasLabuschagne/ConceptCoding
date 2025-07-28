#old code
#page_to_scrape = requests.get("https://mrossmodeling.github.io/MRoss-Modeling/")
#soup = BeautifulSoup(page_to_scrape.text, "html.parser")
#authors = soup.findAll("div", attrs={"class":"logoT"})#authors = soup.findAll("div"    --> the tipe the class is safed under ex. div, span, small...
                                                      #attrs={                         --> how its saved.
                                                      #"class":                        --> class would most of the time stay class.
                                                      #"logoT"})                       --> what the previous is named as.

#for author in authors:
#    print(author.text)


from bs4 import BeautifulSoup
import requests
import os

# Function to read existing companies from file
def read_companies_from_file(filename):
    if os.path.exists(filename):
        with open(filename, 'r', encoding='utf-8') as file:
            return set(line.strip() for line in file)
    return set()

# Function to write unique companies to file
def write_companies_to_file(filename, companies):
    with open(filename, 'a', encoding='utf-8') as file:
        for company in companies:
            file.write(company + '\n')

# URL to scrape
url = "https://mrossmodeling.github.io/MRoss-Modeling/"
page_to_scrape = requests.get(url)
soup = BeautifulSoup(page_to_scrape.text, "html.parser")

# Extract companies
companies = soup.findAll("div", attrs={"class": "logoT"})
companies_text = set(company.text.strip() for company in companies)

# File to save companies
filename = "companies.txt"

# Read existing companies from file
existing_companies = read_companies_from_file(filename)

# Find new companies by checking for duplicates
new_companies = companies_text - existing_companies

# Write new companies to file
if new_companies:
    write_companies_to_file(filename, new_companies)
    print("New companies added to file.")
else:
    print("No new companies found.")

# Optionally, print all companies (new and existing)
#print("All companies:")
#all_companies = existing_companies | new_companies
#for company in all_companies:
#    print(company)
