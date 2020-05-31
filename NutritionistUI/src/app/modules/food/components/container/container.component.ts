import { Component, OnInit, Input } from '@angular/core';
import { Food } from '../../food';
import { FoodService } from '../../food.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'food-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit{
@Input()
foods : Array<Food>;


@Input()
useFavListApi : boolean;
foodJson : any;
  constructor(private service : FoodService, private snackBar : MatSnackBar) { }

  ngOnInit() {
  }

  addFoodToFavList(food){
    console.log("in container"+ food);
    let message=`${food.name} add to favouritlist`;
    
    this.foodJson={
    
      ndbno : food.ndbno,
      name : food.name

    }
    console.log(this.foodJson);
     this.service.addFood(this.foodJson).subscribe((food)=>{
      console.log("in container"+JSON.stringify(food)); 
     
      this.snackBar.open(message,"", {
        duration : 1000
      });
    }); 
  }

  deleteFoodFromFavList(food){
    let message=`${food.name} removed from watchlist`;
    for(var i=0; i<=this.foods.length; i++){
      if(this.foods[i].name===food.name){
        this.foods.splice(i,1);
      }
    }
     this.service.deleteFood(food).subscribe((food)=>{
      this.snackBar.open(message,"", {
        duration : 1000
      });
    }); 

  }

}
