import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-idverify',
  templateUrl: './idverify.component.html',
  styleUrls: ['./idverify.component.scss'],
})

export class IdVerifyComponent implements OnInit {
  idNumber: string = ''; // Binds to the input field
  result: any = null; // Stores the verification results
  loading: boolean = false; // To track the loading state

  constructor() {}

  ngOnInit(): void {}

  onSearch(): void {
    if (this.idNumber.length === 13) {
      this.loading = true; //Show loader
      this.result = null; // Reset the result
      setTimeout(() => {
        this.result = this.checkIdNumber(this.idNumber);
        this.loading = false;
      }, 700) // Simulate delay for validation
    } else{
      this.result = null;
      alert('Please enter a valid 13-digit ID number.');
    }
  }
  onKeyDown(event: KeyboardEvent): void {
    if (event.key === 'Enter') {
      this.onSearch(); // Call the search method when 'Enter' is pressed
    }
  }

  private checkIdNumber(idNumber: string): any {
    const result = {
      dob: null,
      age: null,
      gender: null,
      citizenship: null,
      error: null,
      isValid: null,
    };

    const year = parseInt(idNumber.substring(0, 2));
    const month = parseInt(idNumber.substring(2, 4));
    const day = parseInt(idNumber.substring(4, 6));
    const genderDigit = parseInt(idNumber.substring(6, 7));
    const citizenshipDigit = parseInt(idNumber.substring(10, 11));

    const fullYear = year <= new Date().getFullYear() % 100 ? 2000 + year : 1900 + year;

    //valid date check
    const dob = new Date(fullYear, month - 1, day);
    if (isNaN(dob.getTime()) || dob.getDate() !== day || dob.getMonth() + 1 !== month) {
      result.error = 'Invalid date of birth.';
      return result;
    }
    result.dob = dob;
    result.age = new Date().getFullYear() - dob.getFullYear();

    //Gender Determination
    result.gender = genderDigit < 5 ? 'Female' : 'Male';

    //Citizenship
    switch (citizenshipDigit) {
      case 0:
        result.citizenship = 'SA Citizen';
        break;
      case 1:
        result.citizenship = 'Non-SA Citizen';
        break;
      case 2:
        result.citizenship = 'Refugee';
        break;
      default:
        result.error = 'Invalid citizenship code.';
        return result;
    }

    //check if id is valid
    result.isValid = this.isValidSAId(idNumber);

    return result;
  }
  //ID Validation
  private isValidSAId(idNumber: string): boolean {
    let sum = 0;
    for (let i = 0; i < 13; i++) {
      let digit = parseInt(idNumber[i]);
      if (i % 2 === 1) {
        digit *= 2;
        if (digit > 9) digit -= 9;
      }
      sum += digit;
    }
    return sum % 10 === 0;
  }
}

