<template>
  <div>
    <Row class="expand-row">
      <#list fList as fi>
      <i-col span="6">
        <span class="expand-key">${fi.comment}</span>
        <span class="expand-value">{{row.${fi.name}}}</span>
      </i-col>
    </#list>
    </Row>
  </div>
</template>
<script>
  export default {
    props: {row: Object}
  };
</script>
<style scoped>
  .expand-row {
    margin-bottom: 6px;
  }
  .expand-key {
    font-weight: bold;
    line-height: 25px;
  }
  .expand-value {}
</style>
 