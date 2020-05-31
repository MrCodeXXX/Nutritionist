import { Component, OnInit } from '@angular/core';
import { FoodService } from '../../food.service';

@Component({
  selector: 'food-viewdetails',
  templateUrl: './viewdetails.component.html',
  styleUrls: ['./viewdetails.component.css']
})
export class ViewdetailsComponent implements OnInit {
 foodDetails : any;
 name1 : any;
 nutrientArray:Array<any>;
 nutrientDetails: any;
 displayedColumns : Array<string>;
  constructor(private service : FoodService) { }

  ngOnInit() {
    this.service.showDetail().subscribe((data)=>{
      this.foodDetails=data["foods"][0]["food"]["desc"]["name"];
      console.log(this.foodDetails);
      this.nutrientDetails=data["foods"][0]["food"]["nutrients"];
      this.nutrientArray=this.nutrientDetails;
      console.log("arra"+this.nutrientArray);
      for(let i=0;i<this.nutrientArray.length;i++){
        //console.log(this.nutrientArray[i]);
        let nutName=this.nutrientArray[i]["name"];
        
        
      }
      console.log(this.nutrientDetails);
      this.displayedColumns= ['name', 'value', 'unit'];
    //   let myFood=this.foodDetails[0];
    //  let food=myFood["food"];
    //  let desc =  food["desc"];
    //  let name=desc["name"];
    //  this.name1=name;
      // let nutrients=
      
    }
    
    )
  }

  

}
