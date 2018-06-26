/**
 * Created by Odmin on 14.06.2018.
 */
Ext.define('BizDash.store.Student', {
    extend: 'Ext.data.Store',
    alias: 'store.student',

    requires: [
        'BizDash.model.StudentDTO'
    ],


    model: 'BizDash.model.StudentDTO',
    autoLoad: true,
    autoSync: true,

    proxy: {
        type: 'rest',
        url: 'http://127.0.0.1:8088/students',
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        writer: {
            type: 'json'
        }
    }
});