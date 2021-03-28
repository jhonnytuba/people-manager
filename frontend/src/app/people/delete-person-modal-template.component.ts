import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { PeopleService } from '../core/services/people.service';
import { Person } from '../core/models/person';
import { Subject } from 'rxjs';
import { AlertModalService } from '../core/services/alert-modal.service';

@Component({
  selector: 'app-delete-person-modal-content',
  template: `
    <div class="modal-header">
      <h4 class="modal-title pull-left">Deletar uma Pessoa</h4>
      <button type="button" class="close pull-right" aria-label="Fechar" (click)="bsModalRef.hide()">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <p>
        Você está certo que irá remover a pessoa abaixo?
        <br/>
        <span class="font-weight-bold">{{item.name}}</span>
      </p>
      <p class="text-danger"><small>Essa ação não poderá ser desfeita.</small></p>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-default" (click)="cancel()">Cancelar</button>
      <button type="button" class="btn btn-danger" (click)="delete()" [disabled]="isDeleting">Deletar</button>
    </div>
  `
})
export class DeletePersonModalTemplateComponent implements OnInit {

  public onClose: Subject<boolean> = new Subject<boolean>();

  item!: Person;
  isDeleting = false;

  constructor(public bsModalRef: BsModalRef,
              private peopleService: PeopleService,
              private alertModalService: AlertModalService) {
  }

  ngOnInit(): void {
  }

  cancel(): void {
    this.onClose.next(false);
    this.bsModalRef.hide();
  }

  delete(): void {
    this.isDeleting = true;
    this.peopleService.delete(this.item.id).subscribe(
      () => {
        this.alertModalService.toastSuccess('Pessoa "' + this.item.name + '" deletada com sucesso!');
        this.onClose.next(true);
        this.bsModalRef.hide();
      },
      () => {
        this.alertModalService.toastError('Erro ao deletar a pessoa "' + this.item.name + '!');
      },
      () => this.isDeleting = false);
  }
}
