import { Component, OnInit } from '@angular/core';
import { FoodService } from '../../food.service';
import { Food } from '../../food';

@Component({
  selector: 'food-favlist',
  templateUrl: './favlist.component.html',
  styleUrls: ['./favlist.component.css']
})
export class FavlistComponent implements OnInit {
  foods : Array<Food>;
  useFavListApi=true;
  constructor(private service : FoodService) {
    this.foods=[];
   }

  ngOnInit() {
    this.service.getMyFavList().subscribe((foods)=>{
      console.log(foods);
      this.foods.push(...foods);
    });
  }
 

}
