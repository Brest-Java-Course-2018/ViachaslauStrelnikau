/**
 * Created by Odmin on 22.06.2018.
 */
Ext.define('BizDash.store.LangStore', {
    extend: 'Ext.data.Store',
    alias: 'store.language',
    requires: [
        'BizDash.model.Language'
    ],
    storeId: 'langStore',
    model: 'BizDash.model.Language',

    data: [
        ['en', 'English'],
        ['ru-Ru', 'Russian']
    ]
});