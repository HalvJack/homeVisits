import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainService} from "../../services/main/main.service";
import { MainComponent} from "../../components/main/main.component";

@NgModule({
  declarations: [MainComponent],
  imports: [
    CommonModule
  ],
  providers: [MainService]
})
export class MainModule { }
