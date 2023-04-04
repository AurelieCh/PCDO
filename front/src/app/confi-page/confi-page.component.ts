import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-confi-page',
  templateUrl: './confi-page.component.html',
  styleUrls: ['./confi-page.component.scss']
})
export class ConfiPageComponent {
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });
  isLinear = false;

  foods: any[] = [
    {value: 'steak-0', viewValue: 'novice'},
    {value: 'pizza-1', viewValue: 'amateur'},
    {value: 'tacos-2', viewValue: 'avancé'},

  ];

  constructor(private _formBuilder: FormBuilder) {}
}


