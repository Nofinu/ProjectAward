import type { LayoutServerLoad } from './$types';

export const load: LayoutServerLoad = async ({ cookies }) => {
  return {
    username: cookies.get('twitch_username') ?? null,
  };
};