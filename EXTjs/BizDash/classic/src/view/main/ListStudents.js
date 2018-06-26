/**
 * Created by Odmin on 14.06.2018.
 */
Ext.define('BizDash.view.main.ListStudents', {
    extend: 'Ext.grid.Panel',
    xtype: 'liststudents',

    requires: [
        'BizDash.model.StudentDTO',
        'BizDash.store.Student'
    ],
    title:'Students',
    store: {
        type: 'student'
    },
    model:'BizDash.model.StudentDTO',

    tools: [
        {
            xtype: 'form',
            items: [{
                xtype: 'button',
                text: 'Details',
            }, {
                xtype: 'button',
                text: 'Details',
            }]
        }
    ],
    columns:[
        { text: 'StudentId',     dataIndex: 'studentId' },
        { text: 'Student Name',      dataIndex: 'studentName',flex:1 },
        { text: 'Student Birth',    dataIndex: 'studentBirth' ,xtype: 'datecolumn',format: 'Y-m-d',flex:1},
        { text: 'Student Avg Marks',   dataIndex: 'studentAvgMarks' ,flex:1},
        { text: 'Full Name', dataIndex: 'fullName',flex:1 },
        {
            xtype : 'widgetcolumn',
            width : 100,
            text  : 'Action',
            widget: {
                xtype    : 'button',
                text     : 'Details',
                handler:	'onClickButton'
            }
        }
    ]
});