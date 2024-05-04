<template>
    <div class="aside">
        <h3>Aside</h3>
        <Item />
        <Item />
    </div>
</template>

<script>
import Item from "./Item.vue"
export default{
    components:{
        Item
    }
}
</script>
<style>
.aside{
    float: right;
    width: 30%;
    height: 400px;
    border: 5px solid #999;
    box-sizing: border-box;
}
</style>