/*${cName}模拟数据},作者:${auth},日期:${time}*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/${lowUpp}/queryPage': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        <#list fList as fi>
            <#if fi.type == "String">
                ${fi.name} : "@word(5,10)",// ${fi.comment}
            <#else>
                ${fi.name}: "@integer(100,200)",//${fi.comment}
            </#if>
       </#list>
      }],
      number: '@integer(100,200)',
      size: 10,
      totalElements: 500,
    });
    setTimeout(function () {
      res.json(data);
    }, 500);
  },

  'POST /api/${lowUpp}/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },

  'POST /api/${lowUpp}/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },

  'POST /api/${lowUpp}/queryList': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        <#list fList as fi>
            <#if fi.type == "String">
                ${fi.name} : "@word(5,10)",// ${fi.comment}
            <#else>
                ${fi.name}: "@integer(100,200)",//${fi.comment}
            </#if>
       </#list>
      }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },

  'POST /api/${lowUpp}/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}