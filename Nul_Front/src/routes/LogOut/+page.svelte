<script>
    import { onMount } from "svelte";
    import {userName} from '../../Store/UserName.ts'
    import { goto } from "$app/navigation";


    onMount(() => {
        sessionStorage.removeItem("Token")
        sessionStorage.removeItem("UserName")
        $userName = ""
        goto("/")
	});
</script>