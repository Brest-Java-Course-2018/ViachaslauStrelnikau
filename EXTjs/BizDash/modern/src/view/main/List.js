/**
 * This view is an example list of people.
 */
Ext.define('BizDash.view.main.List', {
    extend: 'Ext.grid.Grid',
    xtype: 'mainlist',

    requires: [
        'BizDash.model.GroupDTO',
        'BizDash.store.GroupDTOstore'
    ],

    title: 'Personnel',

    store: {
        type: 'groupDTOstore'
    },
    model:'BizDash.model.GroupDTO',
    columns: [
        { text: 'Group Id',  dataIndex: 'groupId' },
        { text: 'FullName', dataIndex: 'fullName', flex: 1 },
        { text: 'GroupAvgMarks', dataIndex: 'groupAvgMarks',flex: 1 }
    ],

    listeners: {
        select: 'onItemSelected',
    }
});
