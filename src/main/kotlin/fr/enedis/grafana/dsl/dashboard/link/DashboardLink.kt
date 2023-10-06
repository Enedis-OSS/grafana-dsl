package fr.enedis.grafana.dsl.dashboard.link

import org.json.JSONObject
import fr.enedis.grafana.dsl.json.Json

/**
 * Link object for dashboard
 *
 * @see <a href="https://grafana.com/docs/guides/whats-new-in-v2-1/#dashboard-links-and-navigation">Dashboard links</a>
 *
 * @author Ilya Doroshenko
 * @since 17.10.2019
 */
interface DashboardLink : Json<JSONObject>
