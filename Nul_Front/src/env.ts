import { defineEnvVars } from '@sveltejs/kit/env';

export const variables = defineEnvVars({
	TWITCH_URL: {},
    CLIENT_ID: {},
    CLIENT_SECRET:{}
});