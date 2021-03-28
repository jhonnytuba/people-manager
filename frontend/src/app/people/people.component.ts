import { Component, OnInit } from '@angular/core';
import { PeopleService } from '../core/services/people.service';
import { Person } from '../core/models/person';
import { BsModalService } from 'ngx-bootstrap/modal';
import { DeletePersonModalTemplateComponent } from './delete-person-modal-template.component';
import { SavePersonModalTemplateComponent } from './save-person-modal-template.component';

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.scss']
})
export class PeopleComponent implements OnInit {

  people: Person[] = [];

  constructor(private peopleService: PeopleService,
              private bsModalService: BsModalService) {
  }

  ngOnInit(): void {
    this.findAll();
  }

  delete(item: Person): void {
    const bsModalRef = this.bsModalService.show(DeletePersonModalTemplateComponent, {
      initialState: {item}
    });
    // @ts-ignore
    bsModalRef.content.onClose.subscribe((result) => {
      if (result) {
        this.findAll();
      }
    });
  }

  addNew(): void {
    this.showSaveModal();
  }

  edit(item: Person): void {
    this.showSaveModal(item);
  }

  private showSaveModal(item?: Person): void {
    const bsModalRef = this.bsModalService.show(SavePersonModalTemplateComponent, {
      initialState: {item: item || new Person()}
    });
    // @ts-ignore
    bsModalRef.content.onClose.subscribe((result) => {
      if (result) {
        this.findAll();
      }
    });
  }

  private findAll(): void {
    this.peopleService.findAll()
      .subscribe(values => this.people = values);
  }
}
