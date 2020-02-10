<template>
  <v-container>
    <SelectMenu :a="menuInfo"></SelectMenu>
  </v-container>
</template>

<script>
import SelectMenu from "../components/SelectMenu";
export default {
  name: "SelectedMenu",
  components: {
    SelectMenu
  },
  data() {
    return { menuInfo: [] };
  }
};
</script>

<style>
</style>