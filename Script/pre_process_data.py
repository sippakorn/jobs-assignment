'''
This script will load JSON data record by record and insert it into SQLite database.
It will decompose 1 survey record into 4 associated data as describe below.
- Employer
- Location
- Job
- JobRecord
After successfully clean up and structuring data. This script will produce SQL scribe in which contain all data.
This SQL data will be used to inject into in-memory database of Spring Application.
'''
import json

survey_file = open('../salary_datasets/salary_survey-3.json', encoding="utf8")

data = json.load(survey_file)

for record in data:
    print(record)

survey_file.close()