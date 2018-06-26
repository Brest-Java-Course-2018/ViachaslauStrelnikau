/**
 * Created by Odmin on 07.06.2018.
 */
Ext.define('BizDash.view.main.ListController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.List',

    requires: [
        'BizDash.model.Group'
    ],
    stores: 'BizDash.store.GroupDTOstore',

    /**
     * onAddGroupClick Add group button hit action.
     */
    onAddGroupClick: function () {
        this.createDialog(null);
    },
    /**
     * onEditGroupClick Edit group button hit action.
     * @param button button sender
     */
    onEditGroupClick: function (button) {
        var tempRecord = button.up('panel').getWidgetRecord();
        var record = BizDash.model.Group.load(tempRecord.data.groupId);
        this.createDialog(record);
    },
    /**
     * onItemDoubleClicked Edit group on cel click.
     * @param sender action sender
     * @param record
     */
    onItemDoubleClicked: function (sender, record) {
        var record_object = BizDash.model.Group.load(record.data.groupId);
        this.createDialog(record_object);
    },
    /**
     * createDialog creates add/edit record dialog.
     * @param record updating record
     */
    createDialog: function (record) {
        var view = this.getView();
        this.isEdit = !!record;
        this.dialog = view.add({
            xtype: 'groupEditDialog',
            viewModel: {
                data: {
                    title: record ? 'Edit Group' : 'Add Group'
                },
                // If we are passed a record, a copy of it will be created in the newly spawned session.
                // Otherwise, create a new phantom company in the child.
                links: {
                    Group: record || {
                        type: 'BizDash.model.Group',
                        create: true
                    }
                }
            }
        });
        this.dialog.show();
    },
    /**
     * method close edit dialog.
     * @param button
     */
    onCancelGroupClick: function (button) {
        Ext.destroy(button.up('groupEditDialog'));
    },
    /**
     * method onSaveGroupClick saves added/edited record.
     * @param button
     */
    onSaveGroupClick: function (button) {
        var rec = button.findParentByType('groupEditDialog').getViewModel().get('Group');
        var validationResult=rec.getValidation();
        console.log(validationResult);
        rec.save(
            {
                success: function (record, operation) {
                    Ext.getStore('groupStore').reload();
                },
                failure: function (record, operation) {
                    Ext.Msg.alert('Error while adding record!','Perhaps the group with such name is exist allready!');
                }
            }
        );
        Ext.destroy(button.up('groupEditDialog'));
        // Ext.getStore('groupStore').reload();
        // Ext.destroy(button.up('groupEditDialog'));
    },
    /**
     * method onRemoveGroupClick is calling when remove button is hit.
     * @param button sender
     */
    onRemoveGroupClick: function (button) {
        var record = button.up('panel').getWidgetRecord();
        Ext.Msg.confirm('Warning', 'Are you sure want to delete record:<p>'+ record.data['fullName'] +' ?', 'onConfirm', this);
    },
    /**
     * method onConfirm is calling when user confirms delete of record.
     * @param choice users choice
     */
    onConfirm: function (choice) {
        if (choice === 'yes') {
            var delId=this.getView().getView().eventPosition.record.data['groupId']
            var delRecord=Ext.create('BizDash.model.Group',{groupId:delId,shortName:'',fullName:'',description:''});
           // var res= delRecord.erase();
            delRecord.erase(
                {
                    success: function (record, operation) {
                        Ext.getStore('groupStore').reload();
                    },
                    failure: function (record, operation) {
                        console.log('Error!');
                        Ext.Msg.alert('Error while delete record!','Perhaps the group is not empty!');
                    }
                }
            );
       }
    },

    onClickButton: function (btn) {
        var record = btn.getWidgetRecord();
        Ext.Msg.alert('Details', 'GroupId:' + record.data.groupId + '<p>FullName:' + record.data.fullName + '<p>GroupAvgMarks:' + record.data.groupAvgMarks);
        //   console.log('Name:'+record.data.name+'\nemail:'+record.email+'\nphone:'+record.phone);
    }

});