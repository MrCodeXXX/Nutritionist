import { Component, OnInit } from '@angular/core';
import { Food } from '../../food';
import { FoodService } from '../../food.service';

@Component({
  selector: 'food-fooddb',
  templateUrl: './fooddb.component.html',
  styleUrls: ['./fooddb.component.css']
})
export class FooddbComponent implements OnInit {
  foods : Array<Food>;
  constructor(private foodService : FoodService) {
    this.foods=[];
   }

  ngOnInit() {
    this.foodService.getFoods().subscribe((foods)=>{
      console.log(foods);
    
    //  console.log("Hello"+JSON.stringify(foods[0]));
      this.foods.push(...foods);
      console.log(this.foods);
    })
  }

}
