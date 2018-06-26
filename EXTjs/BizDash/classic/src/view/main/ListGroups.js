/**
 * This view is an example list of people.
 */
Ext.define('BizDash.view.main.ListGroups', {
    extend: 'Ext.grid.Panel',
    xtype: 'listgroups',
    reference: 'groupsGrid',
    requires: [
        'BizDash.store.GroupDTOstore',
        'BizDash.view.main.ListController'
    ],
    controller: 'List',
    title: '<h1>Groups</h1>',

    store: {
        type: 'groupDTOstore'
    },
    tbar: [{
        xtype: 'button',
        text: 'Add',
        id: 'myButton2',
        iconCls:'fa fa-plus',
        listeners: {
            click: 'onAddGroupClick'
        }
    }],
    columnLines: true,


    columns: [
        {text: 'Group Id', dataIndex: 'groupId', align: 'right'},
        {text: 'Full Name', dataIndex: 'fullName', flex: 1, align: 'right'},
        {
            text: 'Group Avg Marks', dataIndex: 'groupAvgMarks', flex: 1, align: 'right',
            renderer: function (val) {
                if (val == -1) return 0;
                else  return val;
            }
        },
        {
            xtype: 'widgetcolumn',
            text: 'Action',
            flex: 1,
            align: 'left',
            widget: {
                xtype: 'panel',
                items: [{
                    xtype: 'button',
                    text: 'Edit',
                    iconCls:'fa fa-pencil',
                    listeners: {click: 'onEditGroupClick'}
                }, {
                    xtype: 'button',
                    text: 'Remove',
                    iconCls:'fa fa-trash',
                    listeners: {click: 'onRemoveGroupClick'}
                    }
                ]
            }
        },
    ],

    listeners: {
        itemdblclick: 'onItemDoubleClicked'
    }

});
