import { Injectable } from '@angular/core';
import { ListType} from "../object/ListType";

@Injectable({
  providedIn: 'root'
})
export class ProductTypeService {

  constructor() {

  }

  getAllType(){
    return ListType;
  }


}
