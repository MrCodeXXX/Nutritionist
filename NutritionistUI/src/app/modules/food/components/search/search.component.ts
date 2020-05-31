import { Component, OnInit } from '@angular/core';
import { Food } from '../../food';
import { FoodService } from '../../food.service';
import { HttpClient, HttpBackend } from '@angular/common/http';

@Component({
  selector: 'food-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  foods : Array<Food>;
  private httpClient : HttpClient;
  constructor(private service : FoodService, private handler : HttpBackend) { 
    this.foods=[];
    this.httpClient = new HttpClient(handler);
  }

  ngOnInit() {
  }

  onEnter(searchKey) {
    console.log(searchKey)
    this.service.searchFood(searchKey).subscribe(foods => {
      console.log("Hi");
      this.foods = foods;
    })
  }
 

}
