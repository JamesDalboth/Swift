#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
} fs_in;

uniform sampler2D tex;

void main()
{
	color += texture(tex, fs_in.tc);
    color += texture(tex, fs_in.tc + vec2(0.005f,0.005f));
    color += texture(tex, fs_in.tc + vec2(0.005f,-0.005f));
    color += texture(tex, fs_in.tc + vec2(-0.005f,-0.005f));
    color += texture(tex, fs_in.tc + vec2(-0.005f,0.005f));
    color = color / 5.0f;

    if (color.w < 0.1) {
        discard;
    }
}