import { Injectable } from '@angular/core';
import { Food } from './food';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {retry} from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class FoodService {
  foods : Array<Food>;

  fdbEndPoint : string;
  
  apiKey : string;
 
  springEndPoint : string;
  search : string;
  ndbno : string;
  constructor(private http : HttpClient) { 
    
    
    this.apiKey="api_key=IfxOmcS32KDNNimN6W2EN1iTrdehAXOg7PxEyjfZ";
    this.fdbEndPoint="https://api.nal.usda.gov/ndb/V2/reports?ndbno=01009&ndbno=01009&ndbno=45202763&ndbno=35193&type=b&format=json&";
    
   
    this.springEndPoint="http://localhost:9041/api/v1/favouriteservice/food";
    this.search="https://api.nal.usda.gov/ndb/search/?format=json&q=";

  }
  sFood : any;
  getFoods():Observable<Array<Food>>{

    
    const endPoint=`${this.fdbEndPoint}&${this.apiKey}`;
    console.log(endPoint);
    return this.http.get(endPoint).pipe(
      retry(3),
      map(this.pickFood),
     
    );

    
  
  }

  pickFood(response){
    return response['foods'];
  }

  pickFoodForSearch(response){
    return response['list']['item'];
  }

  addFood(food){
    console.log(food);
    return this.http.post(this.springEndPoint, food);
  }

  
  searchFood(searchKey: string): Observable<Array<Food>> {
    if (searchKey.length > 0) {
      console.log("in service");
      const searchEndpoint = `${this.search}${searchKey}&sort=n&max=25&offset=0&${this.apiKey}`;
      console.log(searchEndpoint);
      return this.http.get(searchEndpoint).pipe(
        
        map(this.pickFoodForSearch),
        
      );
    }
  }
  showDetails(ndbno){
    this.ndbno=ndbno;
   
   
  }


  showDetail(){
    const showUrl="https://api.nal.usda.gov/ndb/V2/reports?ndbno="+this.ndbno+"&type=b&format=json&"+this.apiKey
    
     return this.http.get(showUrl);
   }

   getMyFavList(){
    return this.http.get<Array<Food>>(this.springEndPoint);
   }

   deleteFood(food :Food){
    const url =`${this.springEndPoint}/${food.id}`;
    return this.http.delete(url,{responseType : 'text'});
   }
 
   updateFood(food){
    const url=`${this.springEndPoint}/${food.id}`;
    return this.http.put(url, food);
  }
}

