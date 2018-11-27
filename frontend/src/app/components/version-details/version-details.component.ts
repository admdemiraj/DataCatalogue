import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {HospitalService} from "../../shared/hospital.service";
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-version-details',
  templateUrl: './version-details.component.html',
  styleUrls: ['./version-details.component.css']
})
export class VersionDetailsComponent implements OnInit,OnChanges {
  @Input('versionId') versionId;
  @Input('versionName') versionName;
  version:any;
  jsonMetadata:any;
  searchTermVar: String;
  searchTermVer: String;

  constructor(private hospitalService: HospitalService, private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    this.hospitalService.getVersionById(this.versionId).subscribe(ver=>{this.version = ver});
    //this.route.params
    //.switchMap((params: Params) => this.hospitalService.getVersionById(+params['versionId']))
     // .subscribe(ver => this.version = ver);

  }
  ngOnChanges(changes: SimpleChanges){
    if (changes['versionId']) {
      this.hospitalService.getJsonStringByVersionId(this.versionId).subscribe(json=>{this.jsonMetadata=json});
    }

  }

  goBack(): void {
    this.location.back();
  }

  download(blob, filename) {
    if (window.navigator.msSaveOrOpenBlob) // IE10+
      window.navigator.msSaveOrOpenBlob(blob, filename);
    else { // Others
      var a = document.createElement("a"),
        url = URL.createObjectURL(blob);
      a.href = url;
      a.download = filename;
      document.body.appendChild(a);
      a.click();
      setTimeout(function() {
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
      }, 0);
    }
  }

  exportJson(): void {
    //var obj =  this.hospitalService.getJsonStringByVersionId(1);

    //var myArray = [];
    //myArray.push(this.jsonMetadata);
    ///we need a proper request
    const c = JSON.stringify(this.jsonMetadata);
    const file = new Blob([c], {type: 'text/json'});
    this.download(file,"variables_"+this.versionName+".json");
  }
}








