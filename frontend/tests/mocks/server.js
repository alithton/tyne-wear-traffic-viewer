import { setupServer } from 'msw/node'
import { handler } from './handlers'

// This configures a Service Worker with the given request handlers.
export const server = setupServer(handler)